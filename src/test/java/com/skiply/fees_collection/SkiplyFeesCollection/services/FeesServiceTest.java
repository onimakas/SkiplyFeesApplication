package com.skiply.fees_collection.SkiplyFeesCollection.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skiply.fees_collection.entities.Fees;
import com.skiply.fees_collection.repositories.FeesRepository;
import com.skiply.fees_collection.services.FeesServiceImpl;
import com.skiply.fees_collection.exceptions.FeesNotFoundException;

class FeesServiceTest {
    @InjectMocks
    private FeesServiceImpl feesService;

    @Mock
    private FeesRepository feesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFees() {
        Fees feesToCreate = new Fees();
        feesToCreate.setId("12345"); // Set an ID for the fees
        feesToCreate.setCreatedAt(null); // Ensure createdAt is null initially

        when(feesRepository.save(any())).thenReturn(feesToCreate);

        Fees createdFees = feesService.createFees(feesToCreate);

        assertNotNull(createdFees);
        assertNotNull(createdFees.getCreatedAt());
        assertEquals("12345", createdFees.getId());
        assertFalse(createdFees.isExpired());
        verify(feesRepository, times(1)).save(any());
    }

    @Test
    void testGetFeesById() {
        String feesId = "12345";
        Fees fees = new Fees();
        fees.setId(feesId);
        when(feesRepository.findByIdAndDeletedAtIsNull(feesId)).thenReturn(Optional.of(fees));

        Optional<Fees> retrievedFees = feesService.getFeesById(feesId);

        assertTrue(retrievedFees.isPresent());
        assertEquals(feesId, retrievedFees.get().getId());
        verify(feesRepository, times(1)).findByIdAndDeletedAtIsNull(feesId);
    }

    @Test
    void testGetAllFees() {
        List<Fees> feesList = new ArrayList<>();
        feesList.add(new Fees());
        feesList.add(new Fees());
        when(feesRepository.findAllByDeletedAtIsNull()).thenReturn(feesList);

        List<Fees> retrievedFees = feesService.getAllFees();

        assertNotNull(retrievedFees);
        assertEquals(2, retrievedFees.size());
        verify(feesRepository, times(1)).findAllByDeletedAtIsNull();
    }

    @Test
    void testUpdateFees() {
        String feesId = "12345";
        Fees existingFees = new Fees();
        existingFees.setId(feesId);

        Fees updatedFees = new Fees();
        updatedFees.setId(feesId);
        updatedFees.setSchoolId("UpdatedSchoolId");

        when(feesRepository.findByIdAndDeletedAtIsNull(feesId)).thenReturn(Optional.of(existingFees));
        when(feesRepository.save(any())).thenReturn(updatedFees);

        Fees updatedResult = feesService.updateFees(feesId, updatedFees);

        assertEquals("UpdatedSchoolId", updatedResult.getSchoolId());
        verify(feesRepository, times(1)).findByIdAndDeletedAtIsNull(feesId);
        verify(feesRepository, times(1)).save(any());
    }

    @Test
    void testUpdateFeesNotFound() {
        String nonExistentFeesId = "99999";
        Fees updatedFees = new Fees();

        when(feesRepository.findByIdAndDeletedAtIsNull(nonExistentFeesId)).thenReturn(Optional.empty());

        assertThrows(FeesNotFoundException.class, () -> feesService.updateFees(nonExistentFeesId, updatedFees));
    }

    @Test
    void testDeleteFees() {
        String feesId = "12345";
        Fees feesToDelete = new Fees();
        feesToDelete.setId(feesId);

        when(feesRepository.findByIdAndDeletedAtIsNull(feesId)).thenReturn(Optional.of(feesToDelete));

        feesService.deleteFees(feesId);

        assertTrue(feesToDelete.getIsDeleted());
        assertNotNull(feesToDelete.getDeletedAt());
        verify(feesRepository, times(1)).delete(feesToDelete);
    }

    @Test
    void testDeleteFeesNotFound() {
        String nonExistentFeesId = "99999";

        when(feesRepository.findByIdAndDeletedAtIsNull(nonExistentFeesId)).thenReturn(Optional.empty());

        assertThrows(FeesNotFoundException.class, () -> feesService.deleteFees(nonExistentFeesId));
    }
}
