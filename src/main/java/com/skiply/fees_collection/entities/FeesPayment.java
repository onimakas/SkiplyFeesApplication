package com.skiply.fees_collection.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Table(name = "feesPayments")
@NoArgsConstructor
@AllArgsConstructor
public class FeesPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String feesId;

    @NotNull
    private String transactionId;

    @NotNull
    private Double amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyCode currencyCode;

    @NotNull
    private Integer quantity;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    private Instant deletedAt;
}