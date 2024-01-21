package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.School;
import com.student.fees.management.transaction.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        school.setCreatedAt(new Date());
        school.setUpdatedAt(new Date());
        school.setIsDeleted(false);

        return schoolRepository.save(school);
    }

    @Override
    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public void deleteSchool(Long id) {
        Optional<School> optionalSchool = getSchoolById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setIsDeleted(true);
            school.setDeletedAt(new Date());
            schoolRepository.save(school);
        }
    }
}
