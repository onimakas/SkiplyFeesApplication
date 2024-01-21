package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.CardNetwork;
import com.skiply.fees_collection.entities.CardType;
import com.skiply.fees_collection.entities.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String id;
    private String cardNumber;
    private CardType cardType;
    private CardNetwork network;
    private String cardHolderName;
    private CurrencyCode currencyCode;
    private Instant cardExpiryDate;
}
