package com.skiply.fees_collection.services;

import com.skiply.fees_collection.repositories.SchoolRepository;
import com.skiply.fees_collection.entities.School;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School saveSchool(School school) {
        school.setCreatedAt(Instant.now());
        school.setUpdatedAt(Instant.now());
        school.setIsDeleted(false);
        return schoolRepository.save(school);
    }

    @Override
    public Optional<School> getSchoolById(String id) {
        return schoolRepository.findById(id);
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public void deleteSchool(String id) {
        Optional<School> optionalSchool = getSchoolById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setIsDeleted(true);
            school.setDeletedAt(Instant.now());
            schoolRepository.save(school);
        }
        else {
            throw new EntityNotFoundException("School not found with ID: " + id);
        }
    }
}
