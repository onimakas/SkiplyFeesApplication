package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.Fees;

import java.util.List;
import java.util.Optional;

public interface FeesService {
    Fees createFees(Fees fees);
    Fees updateFees(Long feeId, Fees fees);
    Optional<Fees> getFeesById(Long id);
    List<Fees> getAllFees();
    void deleteFees(Long id);
}
