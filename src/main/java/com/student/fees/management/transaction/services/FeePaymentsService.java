package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.FeePayments;

import java.util.List;

public interface FeePaymentsService {

    FeePayments getById(Long id);

    FeePayments save(FeePayments feePayments);

    void delete(Long id);

    List<FeePayments> getAll();

}
