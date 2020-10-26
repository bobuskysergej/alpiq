package com.alpiq.controller;

import com.alpiq.TestUtility;
import com.alpiq.controller.dto.SuggestionDto;
import com.alpiq.controller.dto.SuggestionsDto;
import com.alpiq.controller.mapper.SuggestionDtoMapper;
import com.alpiq.domain.Suggestion;
import com.alpiq.service.SuggestionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionsControllerTest {

    @Mock
    private SuggestionsService service;

    private SuggestionDtoMapper mapper;
    private SuggestionsController controller;

    @Before
    public void setUp() {
        mapper = Mappers.getMapper(SuggestionDtoMapper.class);
        controller = new SuggestionsController(mapper, service);
    }

    @Test
    public void suggestCities() {
        Suggestion suggestion = TestUtility.mockSuggestion();
        when(service.suggestCities(anyString())).thenReturn(asList(suggestion));
        SuggestionsDto suggestionsDto = controller.suggestCities("London");
        assertThat(suggestionsDto.getSuggestionDtos()).hasSize(1);
        assertSuggestion(suggestionsDto.getSuggestionDtos().get(0), suggestion);
    }

    private void assertSuggestion(SuggestionDto suggestionDto, Suggestion suggestion) {
        assertThat(suggestionDto.getName()).isEqualTo(suggestion.getName());
        assertThat(suggestionDto.getScore()).isEqualTo(suggestion.getSimilarityScore());
    }
}
