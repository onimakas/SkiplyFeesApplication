package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.FeesPayment;

import java.util.List;

public interface FeesPaymentService {
    FeesPayment getById(String id);

    List<FeesPayment> getByTransactionId(String transactionId);

    FeesPayment save(FeesPayment feesPayment);

    void delete(String id);

    List<FeesPayment> getAll();
}
