package com.skiply.fees_collection.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private LocalDate studentDob;
    private String studentGrade;
    private String parentEmail;
    private String parentMobileNumber;
    private String schoolId;
    private String schoolName;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isDeleted = false;
    private Instant deletedAt;
}
