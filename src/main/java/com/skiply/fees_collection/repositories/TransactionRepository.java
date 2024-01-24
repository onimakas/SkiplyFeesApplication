package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.School;
import com.skiply.fees_collection.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findAllByDeletedAtIsNull();
    Optional<Transaction> findByIdAndDeletedAtIsNull(String transactionId);
}