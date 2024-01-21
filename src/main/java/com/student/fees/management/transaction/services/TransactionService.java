package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction getById(Long id);

    Transaction save(Transaction transaction);

    void delete(Long id);

    List<Transaction> getAll();

}