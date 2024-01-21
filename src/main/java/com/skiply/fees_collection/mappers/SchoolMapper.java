package com.skiply.fees_collection.mappers;

import com.skiply.fees_collection.dtos.SchoolDto;
import com.skiply.fees_collection.entities.School;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    SchoolDto toSchoolDto(School school);
}
