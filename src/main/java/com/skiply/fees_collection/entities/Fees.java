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
@Table(name = "Fees")
@NoArgsConstructor
@AllArgsConstructor
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String schoolId;

    @NotBlank
    @NotNull
    private String grade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FeesType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FeesFrequency frequency;

    @NotNull
    private double amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyCode currencyCode;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Instant validFrom;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Instant validTill;

    private boolean isExpired=false;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Instant updatedAt;

    private Boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant deletedAt;
}
