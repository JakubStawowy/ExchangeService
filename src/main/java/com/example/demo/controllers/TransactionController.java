package com.example.demo.controllers;

import com.example.demo.entities.TransactionBuffer;
import com.example.demo.entities.TransactionPayment;
import com.example.demo.entities.User;
import com.example.demo.services.EmailService;
import com.example.demo.services.TransactionPaymentService;
import com.example.demo.services.UserAccountService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

@Controller
@SessionAttributes("registeredBuffer")
public class TransactionController {

    private final EmailService emailService;
    private final TransactionPaymentService transactionPaymentService;
    private final UserService userService;

    @Autowired
    public TransactionController(EmailService emailService, TransactionPaymentService transactionPaymentService, UserService userService) {
        this.emailService = emailService;
        this.transactionPaymentService = transactionPaymentService;
        this.userService = userService;
    }

    @PostMapping("registerTransaction")
    public String registerTransaction(final @ModelAttribute("transactionBuffer")TransactionBuffer transactionBuffer,
                                       final @CookieValue(value = "user_id") String id, Model model){
        Optional<User> sender = userService.getUserRepository().findById(Long.valueOf(id));
        sender.ifPresent(value->{

            String transactionCode = generateRandomCode();
            transactionBuffer.setAuthorizationCode(transactionCode);
            emailService.sendEmail(EmailService.senderAddress, value.getEmail(), "Transaction", "Transaction code: "+transactionCode);

            model.addAttribute("code", transactionCode);
            model.addAttribute("authorizeBuffer", new TransactionBuffer());
            model.addAttribute("registeredBuffer", transactionBuffer);
        });
        return "authorize";
    }

    @PostMapping("authorizeTransaction")
    @ResponseBody
    public String authorizeTransaction(final @SessionAttribute("registeredBuffer")TransactionBuffer transactionBuffer, final @CookieValue(value = "user_id") String id,
                                       final @ModelAttribute(value = "authorizeBuffer")TransactionBuffer authorizeBuffer,
                                       HttpSession session){

        if(!transactionBuffer.getAuthorizationCode().equals(authorizeBuffer.getConfirmedAuthorizationCode())){
            return "ERROR: incompatible authorization code";
        }

        session.removeAttribute("registeredBuffer");
        Optional<User> sender = userService.getUserRepository().findById(Long.valueOf(id));
        User receiver = userService.getUserRepository().getUserByEmail(transactionBuffer.getReceiverEmail());

        if(receiver != null){

            emailService.sendEmail(
                    EmailService.senderAddress,
                    receiver.getEmail(),
                    "payment",
                    "Transaction with amount "+transactionBuffer.getAmount()+" registered"
            );

            sender.ifPresent(value->{
                transactionPaymentService.getTransactionPaymentRepository().save(new TransactionPayment(
                        new BigDecimal(transactionBuffer.getAmount()),
                        value.getUserAccount(),
                        receiver.getUserAccount()
                ));
            });

            return "transaction registered";
        }
        else{
            return "Error: no user with this email address";
        }
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
