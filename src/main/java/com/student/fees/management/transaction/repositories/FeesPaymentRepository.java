package com.student.fees.management.transaction.repositories;

import com.student.fees.management.transaction.entity.FeesPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesPaymentRepository extends JpaRepository<FeesPayment, Long> {
}