package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.Fees;

import java.util.List;
import java.util.Optional;

public interface FeesService {
    Fees createFees(Fees fees);
    Fees updateFees(String feeId, Fees fees);
    Optional<Fees> getFeesById(String id);
    List<Fees> getAllFees();
    void deleteFees(String id);
}
