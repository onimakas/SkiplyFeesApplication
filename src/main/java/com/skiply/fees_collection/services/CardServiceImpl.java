package com.skiply.fees_collection.services;

import com.skiply.fees_collection.exceptions.CardNotFoundException;
import com.skiply.fees_collection.repositories.CardRepository;
import com.skiply.fees_collection.entities.Card;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card saveCard(Card card) {
        card.setCreatedAt(Instant.now());
        card.setUpdatedAt(Instant.now());
        card.setIsDeleted(false);
        return cardRepository.save(card);
    }

    @Override
    public Optional<Card> getCardById(String id) {
        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public void deleteCard(String id) {
        Optional<Card> optionalCard = getCardById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setIsDeleted(true);
            card.setDeletedAt(Instant.now());
            cardRepository.save(card);
        }
        else {
            throw new CardNotFoundException("Card not found with ID: " + id);
        }
    }
}