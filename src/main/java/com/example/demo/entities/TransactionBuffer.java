package com.example.demo.entities;

public class TransactionBuffer {

    private String amount;
    private String receiverEmail;
    private String authorizationCode;
    private String confirmedAuthorizationCode;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getConfirmedAuthorizationCode() {
        return confirmedAuthorizationCode;
    }

    public void setConfirmedAuthorizationCode(String confirmedAuthorizationCode) {
        this.confirmedAuthorizationCode = confirmedAuthorizationCode;
    }
}
