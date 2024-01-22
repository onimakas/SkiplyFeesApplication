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

import com.skiply.fees_collection.entities.School;
import com.skiply.fees_collection.repositories.SchoolRepository;
import com.skiply.fees_collection.services.SchoolServiceImpl;

class SchoolServiceTest {
    @InjectMocks
    private SchoolServiceImpl schoolService;

    @Mock
    private SchoolRepository schoolRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSchool() {
        School schoolToSave = new School();
        schoolToSave.setSchoolId("12345"); // Set an ID for the school
        schoolToSave.setCreatedAt(null); // Ensure createdAt is null initially

        when(schoolRepository.save(any())).thenReturn(schoolToSave);

        School savedSchool = schoolService.saveSchool(schoolToSave);

        assertNotNull(savedSchool);
        assertNotNull(savedSchool.getCreatedAt());
        assertEquals("12345", savedSchool.getSchoolId());
        assertFalse(savedSchool.getIsDeleted());
        verify(schoolRepository, times(1)).save(any());
    }

    @Test
    void testGetSchoolById() {
        String schoolId = "12345";
        School school = new School();
        school.setSchoolId(schoolId);
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

        Optional<School> retrievedSchool = schoolService.getSchoolById(schoolId);

        assertTrue(retrievedSchool.isPresent());
        assertEquals(schoolId, retrievedSchool.get().getSchoolId());
        verify(schoolRepository, times(1)).findById(schoolId);
    }

    @Test
    void testGetAllSchools() {
        List<School> schools = new ArrayList<>();
        schools.add(new School());
        schools.add(new School());
        when(schoolRepository.findAll()).thenReturn(schools);

        List<School> retrievedSchools = schoolService.getAllSchools();

        assertNotNull(retrievedSchools);
        assertEquals(2, retrievedSchools.size());
        verify(schoolRepository, times(1)).findAll();
    }

    @Test
    void testDeleteSchool() {
        String schoolId = "12345";
        School schoolToDelete = new School();
        schoolToDelete.setSchoolId(schoolId);
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(schoolToDelete));

        schoolService.deleteSchool(schoolId);

        assertTrue(schoolToDelete.getIsDeleted());
        assertNotNull(schoolToDelete.getDeletedAt());
        verify(schoolRepository, times(1)).save(any());
    }

    @Test
    void testDeleteSchoolNotFound() {
        String nonExistentSchoolId = "99999"; // Assuming this ID does not exist in the database
        when(schoolRepository.findById(nonExistentSchoolId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> schoolService.deleteSchool(nonExistentSchoolId));
    }
}