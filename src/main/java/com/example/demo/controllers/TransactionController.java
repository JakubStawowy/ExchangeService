package com.example.demo.controllers;

import com.example.demo.financialSystem.TransactionBuffer;
import com.example.demo.entities.TransactionPayment;
import com.example.demo.entities.User;
import com.example.demo.services.EmailService;
import com.example.demo.services.TransactionPaymentService;
import com.example.demo.services.UserService;
import com.example.demo.validators.NumberFormatValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Controller
@SessionAttributes("registeredBuffer")
public class TransactionController {

    private final EmailService emailService;
    private final TransactionPaymentService transactionPaymentService;
    private final UserService userService;
    private final NumberFormatValidator numberFormatValidator;

    @Autowired
    public TransactionController(EmailService emailService, TransactionPaymentService transactionPaymentService, UserService userService, NumberFormatValidator numberFormatValidator) {
        this.emailService = emailService;
        this.transactionPaymentService = transactionPaymentService;
        this.userService = userService;
        this.numberFormatValidator = numberFormatValidator;
    }

    @PostMapping("registerTransaction")
    public String registerTransaction(final @ModelAttribute("transactionBuffer")TransactionBuffer transactionBuffer,
                                       final @CookieValue(value = "user_id") String id, Model model){

        if(!numberFormatValidator.checkStringParseToBigDecimal(transactionBuffer.getAmount())){
            model.addAttribute("message", "Wrong number format");
            return "payment";
        }

        Optional<User> sender = userService.getUserRepository().findById(Long.valueOf(id));
        sender.ifPresent(value->{

            String transactionCode = generateRandomCode();
            transactionBuffer.setAuthorizationCode(transactionCode);
            emailService.sendEmail(EmailService.senderAddress, "Transaction", "Transaction code: "+transactionCode, value.getEmail());

            model.addAttribute("code", transactionCode);
            model.addAttribute("authorizeBuffer", new TransactionBuffer());
            model.addAttribute("registeredBuffer", transactionBuffer);
        });
        return "authorize";
    }

    @PostMapping("authorizeTransaction")
    public String authorizeTransaction(final @SessionAttribute("registeredBuffer")TransactionBuffer transactionBuffer, final @CookieValue(value = "user_id") String id,
                                       final @ModelAttribute(value = "authorizeBuffer")TransactionBuffer authorizeBuffer,
                                       HttpSession session, Model model){

        Optional<User> optionalLoggedUser = userService.getUserRepository().findById(Long.valueOf(id));
        session.removeAttribute("registeredBuffer");

        if(optionalLoggedUser.isPresent()){

            User receiver = userService.getUserRepository().getUserByEmail(transactionBuffer.getReceiverEmail());
            User sender = optionalLoggedUser.get();

            if(!transactionBuffer.getAuthorizationCode().equals(authorizeBuffer.getConfirmedAuthorizationCode())){
                model.addAttribute("loggedUser", sender);
                model.addAttribute("message", "Incompatible authorization code");
                model.addAttribute("transactionBuffer", new TransactionBuffer());
                return "payment";
            }

            if(receiver != null){
                transactionPaymentService.getTransactionPaymentRepository().save(new TransactionPayment(
                        new BigDecimal(transactionBuffer.getAmount()),
                        sender.getUserAccount(),
                        receiver.getUserAccount()));

                emailService.sendEmail(
                        EmailService.senderAddress,
                        "payment",
                        "Transaction with amount "+transactionBuffer.getAmount()+" registered",
                        receiver.getEmail(),
                        sender.getEmail()
                );

                makeTransaction(transactionBuffer.getAmount(), sender, receiver);
                model.addAttribute("message", "Transaction registered");
                return "message";
            }
            else{
                model.addAttribute("message", "No user with this email address");
                model.addAttribute("transactionBuffer", new TransactionBuffer());
                return "payment";
            }
        }
        return "redirect:errorFallback";
    }

    private void makeTransaction(@NotEmpty String amount, @NotNull User sender, @NotNull User receiver){

        BigDecimal senderBalance = sender.getUserAccount().getBalance();
        BigDecimal receiverBalance = receiver.getUserAccount().getBalance();
        sender.getUserAccount().setBalance(senderBalance.subtract(new BigDecimal(amount)));
        receiver.getUserAccount().setBalance(receiverBalance.add(new BigDecimal(amount)));

        userService.getUserRepository().save(sender);
        userService.getUserRepository().save(receiver);
    }

    private String generateRandomCode(){

        int leftLimit = 48;
        int rightLimit = 90;
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
