package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.Card;
import com.skiply.fees_collection.repositories.TransactionRepository;
import com.skiply.fees_collection.entities.Transaction;
import com.skiply.fees_collection.entities.TransactionStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<Transaction> getById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setTransactionInitiatedAt(Instant.now());
        transaction.setTransactionUpdatedAt(Instant.now());
        transaction.setCreatedAt(Instant.now());
        transaction.setUpdatedAt(Instant.now());
        transaction.setIsDeleted(false);
        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(String id) {
        Optional<Transaction> optionalTransaction = getById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setIsDeleted(true);
            transaction.setDeletedAt(Instant.now());
            transactionRepository.save(transaction);
        }
        else {
            throw new EntityNotFoundException("Transaction not found with ID: " + id);
        }
    }

    @Override
    public List<Transaction> getAll() { return transactionRepository.findAll(); }

//    @Override
//    public List<Transaction> getAll(String studentId, String paymentModeId) {
//        return transactionRepository.findAll(buildSpecification(studentId, paymentModeId));
//    }
//
//    private Specification<Transaction> buildSpecification(String studentId, String paymentModeId) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (studentId != null) {
//                predicates.add(criteriaBuilder.equal(root.get("studentId"), studentId));
//            }
//
//            if (paymentModeId != null) {
//                predicates.add(criteriaBuilder.equal(root.get("paymentModeId"), paymentModeId));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
}
