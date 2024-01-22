package com.skiply.fees_collection.services;

import com.skiply.fees_collection.dtos.FeesCollectionDto;
import com.skiply.fees_collection.dtos.FeesReceiptDto;

import java.util.List;

public interface FeesCollectionService {
    FeesReceiptDto collectFees(FeesCollectionDto transactionDto);
    FeesReceiptDto getFeesReceiptById(String id);
    List<FeesReceiptDto> getAllFeesReceipts();
}
