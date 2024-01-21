package com.skiply.fees_collection.controllers;

import com.skiply.fees_collection.dtos.TransactionDetailsDto;
import com.skiply.fees_collection.dtos.TransactionCreationDto;
import com.skiply.fees_collection.services.TransactionManagementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final TransactionManagementService transactionManagementService;

    @Autowired
    public TransactionController(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailsDto> getTransaction(@PathVariable String id) {
        try {
            TransactionDetailsDto transaction = transactionManagementService.getTransactionDetailsById(id);
            return ResponseEntity.ok(transaction);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionDetailsDto> createTransaction(@RequestBody TransactionCreationDto transactionDto) {
        TransactionDetailsDto savedTransaction = transactionManagementService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDetailsDto>> getAllTransactions() {
        List<TransactionDetailsDto> transactionDetailsDtos = transactionManagementService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactionDetailsDtos);
    }
//
//    @GetMapping
//    public ResponseEntity<List<Transaction>> getTransactions(
//            @RequestParam(required = false) Long studentId,
//            @RequestParam(required = false) Long paymentModeId) {
//        List<Transaction> transactions;
//
//        // You can add more logic here to handle different combinations of query parameters
//        if (studentId != null && paymentModeId != null) {
//            transactions = transactionService.getAllForStudentAndPaymentMode(studentId, paymentModeId);
//        } else if (studentId != null) {
//            transactions = transactionService.getAllForStudent(studentId);
//        } else if (paymentModeId != null) {
//            transactions = transactionService.getAllForPaymentMode(paymentModeId);
//        } else {
//            transactions = transactionService.getAll();
//        }
//
//        return ResponseEntity.ok(transactions);
//    }

}