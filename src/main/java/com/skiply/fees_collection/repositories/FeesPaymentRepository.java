package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.FeesPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeesPaymentRepository extends JpaRepository<FeesPayment, String> {
    List<FeesPayment> findByTransactionId(String transactionId);
}