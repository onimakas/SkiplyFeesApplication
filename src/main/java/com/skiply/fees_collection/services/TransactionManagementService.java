package com.skiply.fees_collection.services;

import com.skiply.fees_collection.dtos.TransactionCreationDto;
import com.skiply.fees_collection.dtos.TransactionDetailsDto;
import com.skiply.fees_collection.entities.Transaction;

import java.util.List;

public interface TransactionManagementService {
    TransactionDetailsDto createTransaction(TransactionCreationDto transactionDto);
    TransactionDetailsDto getTransactionDetailsById(String id);
    List<TransactionDetailsDto> getAllTransactions();
}
