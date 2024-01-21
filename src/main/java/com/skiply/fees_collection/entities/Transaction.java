package com.skiply.fees_collection.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "Transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @NotNull
    private String studentId;

    @NotNull
    private String schoolId;

    @Column(nullable = false)
    @NotBlank
    private String grade;

    @Column(nullable = false)
    @NotNull
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private CurrencyCode currencyCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private PaymentMode paymentMode;

    @Column(nullable = false)
    @NotBlank
    private String paymentModeId;

    @Column(nullable = false)
    @NotBlank
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private TransactionStatus status;

    @Column(nullable = false)
    @NotNull
    private Instant transactionInitiatedAt;

    @Column(nullable = false)
    @NotNull
    private Instant transactionUpdatedAt;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    private Instant deletedAt;
}