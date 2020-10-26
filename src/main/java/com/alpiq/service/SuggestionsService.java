package com.alpiq.service;

import com.alpiq.domain.City;
import com.alpiq.domain.Suggestion;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionsService {

    private static final double SIMILARITY_THRESHOLD = 0.6;

    private CityService cityService;
    private JaccardSimilarity jaccardSimilarity;

    @Autowired
    public SuggestionsService(CityService cityService) {
        this.cityService = cityService;
        this.jaccardSimilarity = new JaccardSimilarity();
    }

    public List<Suggestion> suggestCities(String cityName) {
        List<Suggestion> suggestions = new ArrayList<>();
        for (City city : cityService.getCities()) {
            Double similarityScore = jaccardSimilarity.apply(cityName, city.getName());
            if (similarityScore > SIMILARITY_THRESHOLD) {
                suggestions.add(new Suggestion(city.getFullName(), similarityScore));
            }
        }
        return suggestions;
    }
}
