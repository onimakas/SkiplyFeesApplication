package com.student.fees.management.transaction.services;
import com.student.fees.management.transaction.entity.Cards;
import java.util.List;
import java.util.Optional;

public interface CardsService {
    Cards saveCard(Cards card);
    Optional<Cards> getCardById(Long id);
    List<Cards> getAllCards();
    void deleteCard(Long id);
}