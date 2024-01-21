package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.CurrencyCode;
import com.skiply.fees_collection.entities.FeesFrequency;
import com.skiply.fees_collection.entities.FeesType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesDto {
    private Long id;
    private String grade;
    private FeesType type;
    private FeesFrequency frequency;
    private double amount;
    private CurrencyCode currencyCode;
    private Instant validFrom;
    private Instant validTill;
    private boolean isExpired;
}
