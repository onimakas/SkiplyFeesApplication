package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesPaymentDto {
    private String feesId;
    private Double amount;
    private CurrencyCode currencyCode;
    private Integer quantity;
    private FeesDto feesDetails;
}
