package com.alpiq.service;

import com.alpiq.domain.Suggestion;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SuggestionsServiceTest {

    private CityService cityService;
    private SuggestionsService suggestionsService;

    @Before
    public void setUp() throws Exception {
        cityService = new CityService();
        suggestionsService = new SuggestionsService(cityService);
    }

    @Test
    public void suggestCities() {
        List<Suggestion> suggestions = suggestionsService.suggestCities("New");
        assertThat(suggestions).isEmpty();

        suggestions = suggestionsService.suggestCities("New York");
        assertThat(suggestions).hasSize(4);

        suggestions = suggestionsService.suggestCities("Liver");
        assertThat(suggestions).hasSize(12);
    }

}
