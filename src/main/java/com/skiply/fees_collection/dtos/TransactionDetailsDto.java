package com.skiply.fees_collection.dtos;

import com.skiply.fees_collection.entities.PaymentMode;
import com.skiply.fees_collection.entities.CurrencyCode;
import com.skiply.fees_collection.entities.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsDto {
    private String id;
    private String grade;
    private String schoolId;
    private Double amount;
    private CurrencyCode currencyCode;
    private PaymentMode paymentMode;
    private String paymentModeId;
    private String referenceNumber;
    private TransactionStatus status;
    private Instant transactionInitiatedAt;
    private Instant transactionUpdatedAt;
    private StudentDto studentDetails;
    private CardDto cardDetails;
    private SchoolDto schoolDetails;
    private List<FeesPaymentDto> feesPayments;
}
