package com.skiply.fees_collection.SkiplyFeesCollection.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.skiply.fees_collection.exceptions.FeesNotFoundException;
import com.skiply.fees_collection.exceptions.FeesPaymentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skiply.fees_collection.entities.FeesPayment;
import com.skiply.fees_collection.repositories.FeesPaymentRepository;
import com.skiply.fees_collection.services.FeesPaymentServiceImpl;
import jakarta.persistence.EntityNotFoundException;

class FeesPaymentServiceTest {
    @InjectMocks
    private FeesPaymentServiceImpl feesPaymentService;

    @Mock
    private FeesPaymentRepository feesPaymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        String paymentId = "12345";
        FeesPayment payment = new FeesPayment();
        payment.setId(paymentId);
        when(feesPaymentRepository.findByIdAndDeletedAtIsNull(paymentId)).thenReturn(Optional.of(payment));

        FeesPayment retrievedPayment = feesPaymentService.getById(paymentId);

        assertNotNull(retrievedPayment);
        assertEquals(paymentId, retrievedPayment.getId());
        verify(feesPaymentRepository, times(1)).findByIdAndDeletedAtIsNull(paymentId);
    }

    @Test
    void testGetByIdNotFound() {
        String nonExistentPaymentId = "99999"; // Assuming this ID does not exist in the database
        when(feesPaymentRepository.findByIdAndDeletedAtIsNull(nonExistentPaymentId)).thenReturn(Optional.empty());

        assertThrows(FeesPaymentNotFoundException.class, () -> feesPaymentService.getById(nonExistentPaymentId));
    }

    @Test
    void testGetByTransactionId() {
        String transactionId = "54321";
        List<FeesPayment> payments = new ArrayList<>();
        payments.add(new FeesPayment());
        payments.add(new FeesPayment());
        when(feesPaymentRepository.findByTransactionId(transactionId)).thenReturn(payments);

        List<FeesPayment> retrievedPayments = feesPaymentService.getByTransactionId(transactionId);

        assertNotNull(retrievedPayments);
        assertEquals(2, retrievedPayments.size());
        verify(feesPaymentRepository, times(1)).findByTransactionId(transactionId);
    }

    @Test
    void testSave() {
        FeesPayment paymentToSave = new FeesPayment();
        paymentToSave.setId("12345"); // Set an ID for the payment
        paymentToSave.setCreatedAt(null); // Ensure createdAt is null initially

        when(feesPaymentRepository.save(any())).thenReturn(paymentToSave);

        FeesPayment savedPayment = feesPaymentService.save(paymentToSave);

        assertNotNull(savedPayment);
        assertNotNull(savedPayment.getCreatedAt());
        assertEquals("12345", savedPayment.getId());
        assertFalse(savedPayment.getIsDeleted());
        verify(feesPaymentRepository, times(1)).save(any());
    }

    @Test
    void testDelete() {
        String paymentId = "12345";
        FeesPayment paymentToDelete = new FeesPayment();
        paymentToDelete.setId(paymentId);
        when(feesPaymentRepository.findByIdAndDeletedAtIsNull(paymentId)).thenReturn(Optional.of(paymentToDelete));

        feesPaymentService.delete(paymentId);

        assertTrue(paymentToDelete.getIsDeleted());
        assertNotNull(paymentToDelete.getDeletedAt());
        verify(feesPaymentRepository, times(1)).save(paymentToDelete);
    }

    @Test
    void testDeleteNotFound() {
        String nonExistentPaymentId = "99999"; // Assuming this ID does not exist in the database
        when(feesPaymentRepository.findByIdAndDeletedAtIsNull(nonExistentPaymentId)).thenReturn(Optional.empty());

        assertThrows(FeesPaymentNotFoundException.class, () -> feesPaymentService.delete(nonExistentPaymentId));
    }

    @Test
    void testGetAll() {
        List<FeesPayment> payments = new ArrayList<>();
        payments.add(new FeesPayment());
        payments.add(new FeesPayment());
        when(feesPaymentRepository.findAllByDeletedAtIsNull()).thenReturn(payments);

        List<FeesPayment> retrievedPayments = feesPaymentService.getAll();

        assertNotNull(retrievedPayments);
        assertEquals(2, retrievedPayments.size());
        verify(feesPaymentRepository, times(1)).findAllByDeletedAtIsNull();
    }
}
