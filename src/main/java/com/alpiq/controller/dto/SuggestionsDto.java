package com.alpiq.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuggestionsDto {

    @JsonProperty("suggestions")
    private List<SuggestionDto> suggestionDtos;
}
