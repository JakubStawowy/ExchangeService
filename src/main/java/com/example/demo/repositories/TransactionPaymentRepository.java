package com.example.demo.repositories;

import com.example.demo.entities.TransactionPayment;
import org.springframework.data.repository.CrudRepository;

public interface TransactionPaymentRepository extends CrudRepository<TransactionPayment, Long> {
}
