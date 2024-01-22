package com.skiply.fees_collection.SkiplyFeesCollection.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skiply.fees_collection.entities.Transaction;
import com.skiply.fees_collection.entities.TransactionStatus;
import com.skiply.fees_collection.repositories.TransactionRepository;
import com.skiply.fees_collection.services.TransactionServiceImpl;

class TransactionServiceTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction() {
        Transaction transactionToSave = new Transaction();
        transactionToSave.setId("12345"); // Set an ID for the transaction
        transactionToSave.setTransactionInitiatedAt(null); // Ensure transactionInitiatedAt is null initially

        when(transactionRepository.save(any())).thenReturn(transactionToSave);

        Transaction savedTransaction = transactionService.save(transactionToSave);

        assertNotNull(savedTransaction);
        assertNotNull(savedTransaction.getTransactionInitiatedAt());
        assertEquals("12345", savedTransaction.getId());
        assertFalse(savedTransaction.getIsDeleted());
        assertEquals(TransactionStatus.COMPLETED, savedTransaction.getStatus());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void testGetTransactionById() {
        String transactionId = "12345";
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        Optional<Transaction> retrievedTransaction = transactionService.getById(transactionId);

        assertTrue(retrievedTransaction.isPresent());
        assertEquals(transactionId, retrievedTransaction.get().getId());
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> retrievedTransactions = transactionService.getAll();

        assertNotNull(retrievedTransactions);
        assertEquals(2, retrievedTransactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testDeleteTransaction() {
        String transactionId = "12345";
        Transaction transactionToDelete = new Transaction();
        transactionToDelete.setId(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transactionToDelete));

        transactionService.delete(transactionId);

        assertTrue(transactionToDelete.getIsDeleted());
        assertNotNull(transactionToDelete.getDeletedAt());
        verify(transactionRepository, times(1)).save(transactionToDelete);
    }

    @Test
    void testDeleteTransactionNotFound() {
        String nonExistentTransactionId = "99999"; // Assuming this ID does not exist in the database
        when(transactionRepository.findById(nonExistentTransactionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> transactionService.delete(nonExistentTransactionId));
    }
}
