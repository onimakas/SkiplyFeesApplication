package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.CurrencyCode;
import com.skiply.fees_collection.entities.PaymentMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesCollectionDto {
    @NotBlank
    private String grade;

    @NotNull
    private String studentId;

    @NotNull
    private String schoolId;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private CurrencyCode currencyCode;

    // PaymentModeDetails --> CardDetails implement, inheritance

    @NotNull
    private PaymentMode paymentMode; // CREDIT_CARD

    @NotBlank
    private String paymentModeId; // card id

    @NotBlank
    private String paymentReferenceNumber;

    @NotNull
    @NotEmpty
    private List<FeesPaymentCreationDto> feesPayments;
}
