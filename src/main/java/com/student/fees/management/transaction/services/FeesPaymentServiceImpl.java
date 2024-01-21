package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.FeesPayment;
import com.student.fees.management.transaction.repositories.FeesPaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeesPaymentServiceImpl implements FeesPaymentService {

    private final FeesPaymentRepository feesPaymentRepository;

    public FeesPaymentServiceImpl(FeesPaymentRepository feesPaymentRepository) {
        this.feesPaymentRepository = feesPaymentRepository;
    }

    @Override
    public FeesPayment getById(Long id) {
        return feesPaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FeePayments not found with id: " + id));
    }

    @Override
    public FeesPayment save(FeesPayment feesPayment) {
        return feesPaymentRepository.save(feesPayment);
    }

    @Override
    public void delete(Long id) {
        feesPaymentRepository.deleteById(id);
    }

    @Override
    public List<FeesPayment> getAll() {
        return feesPaymentRepository.findAll();
    }
}
