package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesRepository extends JpaRepository<Fees, String> {
}