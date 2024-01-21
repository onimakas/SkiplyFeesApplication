package com.student.fees.management.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "student_id")
    @NotNull
    private Student student;*/

    @Column(nullable = false)
    @NotBlank
    private String grade;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @NotNull
    private School school;

    @Column(nullable = false)
    @NotNull
    private Double amount;

    @Column(nullable = false)
    @NotBlank
    private String currencyCode;

    @Column(nullable = false)
    @NotBlank
    private String paymentMode;

    @Column(nullable = false)
    @NotBlank
    private String paymentModeId;

    @Column(nullable = false)
    @NotBlank
    private String referenceNumber;

    @Column(nullable = false)
    @NotBlank
    private String status;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime transactionInitiatedAt;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime transactionUpdatedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;
}