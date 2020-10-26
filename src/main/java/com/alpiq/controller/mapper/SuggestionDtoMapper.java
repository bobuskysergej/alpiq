package com.alpiq.controller.mapper;

import com.alpiq.controller.dto.SuggestionDto;
import com.alpiq.domain.Suggestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuggestionDtoMapper {

    @Mapping(target = "score", source = "suggestion.similarityScore")
    SuggestionDto toSuggestionDto(Suggestion suggestion);
}
