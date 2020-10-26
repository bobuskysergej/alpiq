package com.alpiq;

import com.alpiq.domain.Suggestion;

import java.util.Random;

public class TestUtility {

    private static Random random = new Random();

    public static Suggestion mockSuggestion() {
        return new Suggestion(randomName(), randomScore());
    }

    private static String randomName() {
        return "city-" + random.nextInt();
    }

    private static Double randomScore() {
        return random.nextDouble();
    }
}
