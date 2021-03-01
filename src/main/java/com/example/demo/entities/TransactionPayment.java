package com.example.demo.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction_payment")
public class TransactionPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "transaction_time")
    private Timestamp transactionTime;

    @NotNull
    @Column(columnDefinition = "varchar(255) default 'EUR'")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_account")
    private UserAccount targetAccount;

    @PrePersist
    public void setTransactionTime(){
        transactionTime = new Timestamp(System.currentTimeMillis());
    }

    public TransactionPayment(BigDecimal paymentAmount, UserAccount userAccount, UserAccount targetAccount, String currency) {
        this.paymentAmount = paymentAmount;
        this.userAccount = userAccount;
        this.targetAccount = targetAccount;
        this.currency = currency;
    }

    public TransactionPayment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(UserAccount targetAccount) {
        this.targetAccount = targetAccount;
    }
}
