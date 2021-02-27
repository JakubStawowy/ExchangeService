package com.example.demo.services;

import com.example.demo.repositories.TransactionPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionPaymentService {

    private final TransactionPaymentRepository transactionPaymentRepository;

    @Autowired
    public TransactionPaymentService(TransactionPaymentRepository transactionPaymentRepository) {
        this.transactionPaymentRepository = transactionPaymentRepository;
    }

    public TransactionPaymentRepository getTransactionPaymentRepository() {
        return transactionPaymentRepository;
    }
}
