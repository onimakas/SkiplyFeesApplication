package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.Fees;
import com.student.fees.management.transaction.exceptions.FeesNotFoundException;
import com.student.fees.management.transaction.repositories.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeesServiceImpl implements FeesService {
    private final FeesRepository feesRepository;

    @Autowired
    public FeesServiceImpl(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    @Override
    public Fees createFees(Fees fees) {
        return feesRepository.save(fees);
    }

    @Override
    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public Optional<Fees> getFeesById(Long feesId) {
        Optional<Fees> optionalFees = feesRepository.findById(feesId);
        if (optionalFees.isPresent()) {
            return optionalFees;
        }
        throw new FeesNotFoundException("Fees not found with ID: " + feesId);
    }

    @Override
    public Fees updateFees(Long feesId, Fees feesDetails) {
        Optional<Fees> optionalFees = feesRepository.findById(feesId);
        if (optionalFees.isPresent()) {
            Fees fees = optionalFees.get();
            fees.setSchool(feesDetails.getSchool());
            fees.setGrade(feesDetails.getGrade());
            fees.setType(feesDetails.getType());
            fees.setFrequency(feesDetails.getFrequency());
            fees.setAmount(feesDetails.getAmount());
            fees.setCurrencyCode(feesDetails.getCurrencyCode());
            fees.setValidFrom(feesDetails.getValidFrom());
            fees.setValidTill(feesDetails.getValidTill());
            fees.setExpired(feesDetails.isExpired());
            fees.setCreatedAt(feesDetails.getCreatedAt());
            fees.setUpdatedAt(feesDetails.getUpdatedAt());
            fees.setIsDeleted(feesDetails.getIsDeleted());
            fees.setDeletedAt(feesDetails.getDeletedAt());

            return feesRepository.save(fees);
        }
        throw new FeesNotFoundException("Fees not found with ID: " + feesId);
    }

    @Override
    public void deleteFees(Long feesId) {
        Optional<Fees> optionalFees = feesRepository.findById(feesId);
        if (optionalFees.isPresent()) {
            feesRepository.delete(optionalFees.get());
        } else {
            throw new FeesNotFoundException("Fees not found with ID: " + feesId);
        }
    }
}