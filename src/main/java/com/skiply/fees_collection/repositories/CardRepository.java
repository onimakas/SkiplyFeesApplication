package com.skiply.fees_collection.repositories;

import com.skiply.fees_collection.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByDeletedAtIsNull();
    Optional<Card> findByIdAndDeletedAtIsNull(String cardId);

}