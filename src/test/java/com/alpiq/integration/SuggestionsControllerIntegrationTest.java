package com.alpiq.integration;

import com.alpiq.controller.dto.SuggestionsDto;
import com.alpiq.service.CityService;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SuggestionsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URI suggestionUri;

    @Autowired
    private CityService cityService;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.suggestionUri = new URL("http://localhost:" + port + "/suggestions").toURI();
    }

    @Test
    public void suggestCities() throws Exception {
        URIBuilder uriBuilder = buildGetSuggestionUri("London");
        ResponseEntity<SuggestionsDto> suggestionsResponse = template.getForEntity(uriBuilder.build(), SuggestionsDto.class);
        assertThat(suggestionsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        SuggestionsDto suggestionDtos = suggestionsResponse.getBody();
        assertThat(suggestionDtos.getSuggestionDtos().size()).isEqualTo(7);
        assertThat(suggestionDtos.getSuggestionDtos().get(0).getName()).isEqualTo("London, America/Toronto");
        assertThat(suggestionDtos.getSuggestionDtos().get(0).getScore()).isEqualTo(1.0);
        assertThat(suggestionDtos.getSuggestionDtos().get(5).getName()).isEqualTo("Lynwood, America/Los_Angeles");
        assertThat(suggestionDtos.getSuggestionDtos().get(5).getScore()).isEqualTo(0.6666666666666666);
    }

    private URIBuilder buildGetSuggestionUri(String cityName) {
        return new URIBuilder(suggestionUri).addParameter("q", cityName);
    }
}
