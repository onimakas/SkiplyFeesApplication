package com.skiply.fees_collection.SkiplyFeesCollection.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.skiply.fees_collection.exceptions.CardNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skiply.fees_collection.entities.Card;
import com.skiply.fees_collection.repositories.CardRepository;
import com.skiply.fees_collection.services.CardServiceImpl;

class CardServiceTest {
    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCard() {
        Card cardToSave = new Card();
        cardToSave.setId("12345"); // Set an ID for the card
        cardToSave.setCreatedAt(null); // Ensure createdAt is null initially

        when(cardRepository.save(any())).thenReturn(cardToSave);

        Card savedCard = cardService.saveCard(cardToSave);

        assertNotNull(savedCard);
        assertNotNull(savedCard.getCreatedAt());
        assertEquals("12345", savedCard.getId());
        assertFalse(savedCard.getIsDeleted());
        verify(cardRepository, times(1)).save(any());
    }

    @Test
    void testGetCardById() {
        String cardId = "12345";
        Card card = new Card();
        card.setId(cardId);
        when(cardRepository.findByIdAndDeletedAtIsNull(cardId)).thenReturn(Optional.of(card));

        Optional<Card> retrievedCard = cardService.getCardById(cardId);

        assertTrue(retrievedCard.isPresent());
        assertEquals(cardId, retrievedCard.get().getId());
        verify(cardRepository, times(1)).findByIdAndDeletedAtIsNull(cardId);
    }

    @Test
    void testGetAllCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        when(cardRepository.findAllByDeletedAtIsNull()).thenReturn(cards);

        List<Card> retrievedCards = cardService.getAllCards();

        assertNotNull(retrievedCards);
        assertEquals(2, retrievedCards.size());
        verify(cardRepository, times(1)).findAllByDeletedAtIsNull();
    }

    @Test
    void testDeleteCard() {
        String cardId = "12345";
        Card card = new Card();
        card.setId(cardId);
        when(cardRepository.findByIdAndDeletedAtIsNull(cardId)).thenReturn(Optional.of(card));

        cardService.deleteCard(cardId);

        assertTrue(card.getIsDeleted());
        assertNotNull(card.getDeletedAt());
        verify(cardRepository, times(1)).save(any());
    }

    @Test
    void testGetCardByIdNotFound() {
        String nonExistentCardId = "99999";
        when(cardRepository.findByIdAndDeletedAtIsNull(nonExistentCardId)).thenReturn(Optional.empty());

        Optional<Card> retrievedCard = cardService.getCardById(nonExistentCardId);

        assertTrue(retrievedCard.isEmpty());
        verify(cardRepository, times(1)).findByIdAndDeletedAtIsNull(nonExistentCardId);
    }

    @Test
    void testGetAllCardsEmptyList() {
        when(cardRepository.findAllByDeletedAtIsNull()).thenReturn(Collections.emptyList());

        List<Card> retrievedCards = cardService.getAllCards();

        assertNotNull(retrievedCards);
        assertTrue(retrievedCards.isEmpty());
        verify(cardRepository, times(1)).findAllByDeletedAtIsNull();
    }

    @Test
    void testDeleteCardNotFound() {
        String nonExistentCardId = "99999";
        when(cardRepository.findByIdAndDeletedAtIsNull(nonExistentCardId)).thenReturn(Optional.empty());

        assertThrows(CardNotFoundException.class, () -> cardService.deleteCard(nonExistentCardId));
    }
}
