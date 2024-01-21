package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.FeePayments;
import com.student.fees.management.transaction.repositories.FeePaymentsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeePaymentsServiceImpl implements FeePaymentsService {

    private final FeePaymentsRepository feePaymentsRepository;

    public FeePaymentsServiceImpl(FeePaymentsRepository feePaymentsRepository) {
        this.feePaymentsRepository = feePaymentsRepository;
    }

    @Override
    public FeePayments getById(Long id) {
        return feePaymentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FeePayments not found with id: " + id));
    }

    @Override
    public FeePayments save(FeePayments feePayments) {
        return feePaymentsRepository.save(feePayments);
    }

    @Override
    public void delete(Long id) {
        feePaymentsRepository.deleteById(id);
    }

    @Override
    public List<FeePayments> getAll() {
        return feePaymentsRepository.findAll();
    }
}
