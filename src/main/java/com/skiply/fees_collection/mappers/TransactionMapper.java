package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.TransactionCreationDto;
import com.skiply.fees_collection.dtos.TransactionDetailsDto;
import com.skiply.fees_collection.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CardMapper.class, SchoolMapper.class, FeesPaymentMapper.class})
public interface TransactionMapper {
    Transaction toTransaction(TransactionCreationDto dto);

    TransactionDetailsDto toTransactionDetailsDto(Transaction transaction);
}
