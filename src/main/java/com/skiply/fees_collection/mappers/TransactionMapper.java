package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.FeesCollectionDto;
import com.skiply.fees_collection.dtos.FeesReceiptDto;
import com.skiply.fees_collection.entities.Transaction;
import org.mapstruct.Mapper;

// static
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction(FeesCollectionDto dto);

    FeesReceiptDto toFeesReceiptDto(Transaction transaction);
}
