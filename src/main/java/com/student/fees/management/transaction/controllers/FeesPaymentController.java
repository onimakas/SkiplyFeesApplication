package com.student.fees.management.transaction.controllers;

import com.student.fees.management.transaction.entity.FeesPayment;
import com.student.fees.management.transaction.services.FeesPaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee-payments")
public class FeesPaymentController {

    private final FeesPaymentService feesPaymentService;

    public FeesPaymentController(FeesPaymentService feesPaymentService) {
        this.feesPaymentService = feesPaymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeesPayment> getFeePayment(@PathVariable Long id) {
        try {
            FeesPayment feesPayment = feesPaymentService.getById(id);
            return ResponseEntity.ok(feesPayment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FeesPayment> createFeePayment(@RequestBody FeesPayment feesPayment) {
        FeesPayment savedFeesPayment = feesPaymentService.save(feesPayment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeesPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeePayment(@PathVariable Long id) {
        try {
            feesPaymentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FeesPayment>> getAllFeePayments() {
        List<FeesPayment> feesPaymentList = feesPaymentService.getAll();
        return ResponseEntity.ok(feesPaymentList);
    }

    // Add other methods and exception handlers as needed
}