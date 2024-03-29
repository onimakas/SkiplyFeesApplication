package com.skiply.fees_collection.services;

import com.skiply.fees_collection.exceptions.FeesPaymentNotFoundException;
import com.skiply.fees_collection.repositories.FeesPaymentRepository;
import com.skiply.fees_collection.entities.FeesPayment;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeesPaymentServiceImpl implements FeesPaymentService {
    private final FeesPaymentRepository feesPaymentRepository;

    public FeesPaymentServiceImpl(FeesPaymentRepository feesPaymentRepository) {
        this.feesPaymentRepository = feesPaymentRepository;
    }

    @Override
    public FeesPayment getById(String id) {
        return feesPaymentRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new FeesPaymentNotFoundException("FeePayments not found with id: " + id));
    }

    @Override
    public List<FeesPayment> getByTransactionId(String transactionId) {
        return feesPaymentRepository.findByTransactionId(transactionId);
    }

    @Override
    public FeesPayment save(FeesPayment feesPayment) {
        feesPayment.setCreatedAt(Instant.now());
        feesPayment.setUpdatedAt(Instant.now());
        feesPayment.setIsDeleted(false);
        return feesPaymentRepository.save(feesPayment);
    }

    @Override
    public void delete(String id) {
        Optional<FeesPayment> optionalFeesPayment = feesPaymentRepository.findByIdAndIsDeletedIsFalse(id);
        if (optionalFeesPayment.isPresent()) {
            FeesPayment feesPayment = optionalFeesPayment.get();
            feesPayment.setIsDeleted(true);
            feesPayment.setDeletedAt(Instant.now());
            feesPayment.setUpdatedAt(Instant.now());
            feesPaymentRepository.save(feesPayment);
        } else {
            throw new FeesPaymentNotFoundException("FeesPayment not found with ID: " + id);
        }
    }

    @Override
    public List<FeesPayment> getAll() {
        return feesPaymentRepository.findAllByIsDeletedIsFalse();
    }
}
