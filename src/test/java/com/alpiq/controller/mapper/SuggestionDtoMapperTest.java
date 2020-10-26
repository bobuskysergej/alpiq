package com.alpiq.controller.mapper;

import com.alpiq.controller.dto.SuggestionDto;
import com.alpiq.domain.Suggestion;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static com.alpiq.TestUtility.mockSuggestion;
import static org.assertj.core.api.Assertions.assertThat;

public class SuggestionDtoMapperTest {

    private SuggestionDtoMapper mapper;

    @Before
    public void setUp() throws Exception {
        this.mapper = Mappers.getMapper(SuggestionDtoMapper.class);
    }

    @Test
    public void toSuggestionDtoMapping() {
        Suggestion suggestion = mockSuggestion();
        SuggestionDto suggestionDto = mapper.toSuggestionDto(suggestion);
        assertProducts(suggestionDto, suggestion);
    }

    private void assertProducts(SuggestionDto suggestionDto, Suggestion suggestion) {
        assertThat(suggestionDto.getName()).isEqualTo(suggestion.getName());
        assertThat(suggestionDto.getScore()).isEqualTo(suggestion.getSimilarityScore());
    }
}
