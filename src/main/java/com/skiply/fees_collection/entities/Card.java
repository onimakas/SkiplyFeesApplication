package com.skiply.fees_collection.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Table(name = "Cards")
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @NotNull
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CardNetwork network;

    @NotBlank
    @NotNull
    private String cardHolderName;

    @NotNull
    private CurrencyCode currencyCode;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant cardExpiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant updatedAt;

    private Boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant deletedAt;
}