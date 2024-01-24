package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.Card;
import com.skiply.fees_collection.entities.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeesRepository extends JpaRepository<Fees, String> {
    List<Fees> findAllByDeletedAtIsNull();
    Optional<Fees> findByIdAndDeletedAtIsNull(String feesId);

}