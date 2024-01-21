package com.student.fees.management.transaction.controllers;

import com.student.fees.management.transaction.entity.FeePayments;
import com.student.fees.management.transaction.services.FeePaymentsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee-payments")
public class FeePaymentsController {

    private final FeePaymentsService feePaymentsService;

    public FeePaymentsController(FeePaymentsService feePaymentsService) {
        this.feePaymentsService = feePaymentsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeePayments> getFeePayment(@PathVariable Long id) {
        try {
            FeePayments feePayments = feePaymentsService.getById(id);
            return ResponseEntity.ok(feePayments);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FeePayments> createFeePayment(@RequestBody FeePayments feePayments) {
        FeePayments savedFeePayments = feePaymentsService.save(feePayments);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeePayments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeePayment(@PathVariable Long id) {
        try {
            feePaymentsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FeePayments>> getAllFeePayments() {
        List<FeePayments> feePaymentsList = feePaymentsService.getAll();
        return ResponseEntity.ok(feePaymentsList);
    }

    // Add other methods and exception handlers as needed
}