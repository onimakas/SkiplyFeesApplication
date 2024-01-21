package com.student.fees.management.transaction.repositories;

import com.student.fees.management.transaction.entity.FeePayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePaymentsRepository extends JpaRepository<FeePayments, Long> {

}