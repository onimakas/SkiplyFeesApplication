package com.skiply.fees_collection.services;

import com.skiply.fees_collection.exceptions.FeesNotFoundException;
import com.skiply.fees_collection.repositories.FeesRepository;
import com.skiply.fees_collection.entities.Fees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        fees.setCreatedAt(Instant.now());
        fees.setUpdatedAt(Instant.now());
        fees.setIsDeleted(false);
        return feesRepository.save(fees);
    }

    @Override
    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public Optional<Fees> getFeesById(String feesId) {
        return feesRepository.findById(feesId);
    }

    @Override
    public Fees updateFees(String feesId, Fees feesDetails) {
        Optional<Fees> optionalFees = feesRepository.findById(feesId);
        if (optionalFees.isPresent()) {
            Fees fees = optionalFees.get();
            fees.setSchoolId(feesDetails.getSchoolId());
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
    public void deleteFees(String feesId) {
        Optional<Fees> optionalFees = feesRepository.findById(feesId);
        if (optionalFees.isPresent()) {
            Fees fees = optionalFees.get();
            fees.setIsDeleted(true);
            fees.setDeletedAt(Instant.now());
            fees.setUpdatedAt(Instant.now());
            feesRepository.delete(fees);
        } else {
            throw new FeesNotFoundException("Fees not found with ID: " + feesId);
        }
    }
}