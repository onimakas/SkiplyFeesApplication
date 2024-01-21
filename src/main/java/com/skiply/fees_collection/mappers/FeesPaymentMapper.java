package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.FeesPaymentDto;
import com.skiply.fees_collection.dtos.FeesPaymentCreationDto;
import com.skiply.fees_collection.entities.FeesPayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FeesMapper.class})
public interface FeesPaymentMapper {
    FeesPayment toFeesPayment(FeesPaymentCreationDto dto);

    FeesPaymentDto toFeesPaymentDto(FeesPayment feesPayment);
}
