package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.FeesPayment;

import java.util.List;

public interface FeesPaymentService {
    FeesPayment getById(Long id);

    FeesPayment save(FeesPayment feesPayment);

    void delete(Long id);

    List<FeesPayment> getAll();

}
