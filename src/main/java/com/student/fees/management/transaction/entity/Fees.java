package com.student.fees.management.transaction.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Getter
@Setter
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_Id")
    private School school;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String grade;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String type;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String frequency;

    @NotNull
    private double amount;

    @NotBlank
    @Size(max = 255)
    @NotNull
    private String currencyCode;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date validFrom;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date validTill;

    private boolean isExpired=false;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    private Boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

}
