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
@Table(name = "schools")
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String schoolId;

    @NotBlank
    @NotNull
    private String schoolName;

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
