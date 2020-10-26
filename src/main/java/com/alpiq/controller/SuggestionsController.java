package com.alpiq.controller;

import com.alpiq.controller.dto.SuggestionDto;
import com.alpiq.controller.dto.SuggestionsDto;
import com.alpiq.controller.mapper.SuggestionDtoMapper;
import com.alpiq.domain.Suggestion;
import com.alpiq.service.SuggestionsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/suggestions")
@RestController
public class SuggestionsController {

    private SuggestionDtoMapper dtoMapper;
    private SuggestionsService service;

    @Autowired
    public SuggestionsController(SuggestionDtoMapper dtoMapper, SuggestionsService service) {
        this.dtoMapper = dtoMapper;
        this.service = service;
    }

    @ApiOperation(value = "Retrieves a list of cities that match a giving string.", response = SuggestionsDto.class)
    @GetMapping
    public SuggestionsDto suggestCities(@ApiParam(name = "q", value = "String query that suggestions are going to be based on") @RequestParam("q") String cityName) {
        List<Suggestion> suggestions = service.suggestCities(cityName);
        return new SuggestionsDto(mapToDtos(suggestions));
    }

    private List<SuggestionDto> mapToDtos(List<Suggestion> suggestions) {
        return suggestions.stream()
                .sorted()
                .map(suggestion -> dtoMapper.toSuggestionDto(suggestion)).collect(Collectors.toList());
    }
}
