package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    Card saveCard(Card card);
    Optional<Card> getCardById(String id);
    List<Card> getAllCards();
    void deleteCard(String id);
}