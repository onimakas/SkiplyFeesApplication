package com.student.fees.management.transaction.services;
import com.student.fees.management.transaction.entity.Card;
import java.util.List;
import java.util.Optional;

public interface CardService {
    Card saveCard(Card card);
    Optional<Card> getCardById(Long id);
    List<Card> getAllCards();
    void deleteCard(Long id);
}