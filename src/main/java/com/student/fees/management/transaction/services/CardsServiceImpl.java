package com.student.fees.management.transaction.services;
import com.student.fees.management.transaction.repositories.CardsRepository;
import com.student.fees.management.transaction.entity.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CardsServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;
    @Autowired
    public CardsServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @Override
    public Cards saveCard(Cards card) {
        card.setCreatedAt(new Date());
        card.setUpdatedAt(new Date());
        card.setIsDeleted(false);

        return cardsRepository.save(card);
    }

    @Override
    public Optional<Cards> getCardById(Long id) {
        return cardsRepository.findById(id);
    }

    @Override
    public List<Cards> getAllCards() {
        return cardsRepository.findAll();
    }

    @Override
    public void deleteCard(Long id) {
        Optional<Cards> optionalCard = getCardById(id);
        if (optionalCard.isPresent()) {
            Cards card = optionalCard.get();
            card.setIsDeleted(true);
            card.setDeletedAt(new Date());
            cardsRepository.save(card);
        }
    }
}