package com.student.fees.management.transaction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
public class Cards {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @NotNull
    @Size(max = 255)
    private String cardNumber;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String cardType;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String network;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String cardHolderName;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String currencyCode;


    @NotNull
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date cardExpiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date updatedAt;

    private Boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

}