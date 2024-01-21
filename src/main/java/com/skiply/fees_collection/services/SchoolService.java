package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    School saveSchool(School school);
    Optional<School> getSchoolById(String id);
    List<School> getAllSchools();
    void deleteSchool(String id);
}
