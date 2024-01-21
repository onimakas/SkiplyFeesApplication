package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.CardDto;
import com.skiply.fees_collection.entities.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto toCardDto(Card card);
}
