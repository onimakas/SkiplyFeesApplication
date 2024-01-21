package com.skiply.fees_collection.services;

import com.skiply.fees_collection.dtos.TransactionCreationDto;

public interface TransactionValidationService {
    void validateTransactionCreationDto(TransactionCreationDto dto);
}
