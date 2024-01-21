package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.FeesDto;
import com.skiply.fees_collection.entities.Fees;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeesMapper {
    FeesDto toFeesDto(Fees fees);
}
