package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<Transaction> getById(String id);

    Transaction save(Transaction transaction);

    void delete(String id);

//    List<Transaction> getAll(String studentId, String paymentModeId);
    List<Transaction> getAll();
}