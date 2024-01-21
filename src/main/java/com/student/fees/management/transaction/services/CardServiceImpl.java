package com.student.fees.management.transaction.services;
import com.student.fees.management.transaction.repositories.CardRepository;
import com.student.fees.management.transaction.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card saveCard(Card card) {
        card.setCreatedAt(new Date());
        card.setUpdatedAt(new Date());
        card.setIsDeleted(false);

        return cardRepository.save(card);
    }

    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public void deleteCard(Long id) {
        Optional<Card> optionalCard = getCardById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setIsDeleted(true);
            card.setDeletedAt(new Date());
            cardRepository.save(card);
        }
    }
}