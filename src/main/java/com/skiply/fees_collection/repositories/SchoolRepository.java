package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.Card;
import com.skiply.fees_collection.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {
    List<School> findAllByDeletedAtIsNull();
    Optional<School> findBySchoolIdAndDeletedAtIsNull(String schoolId);

}