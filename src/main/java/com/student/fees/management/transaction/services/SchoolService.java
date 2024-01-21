package com.student.fees.management.transaction.services;

import com.student.fees.management.transaction.entity.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    School saveSchool(School school);
    Optional<School> getSchoolById(Long id);
    List<School> getAllSchools();
    void deleteSchool(Long id);
}
