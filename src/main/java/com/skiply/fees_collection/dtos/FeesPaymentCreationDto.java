package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.CurrencyCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesPaymentCreationDto {
    @NotNull
    private String feesId;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private CurrencyCode currencyCode;

    @NotNull
    @Positive
    private Integer quantity;
}
