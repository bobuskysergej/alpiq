package com.alpiq.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Suggestion implements Comparable<Suggestion> {

    private String name;
    private Double similarityScore;

    @Override
    public int compareTo(Suggestion other) {
        return other.similarityScore.compareTo(similarityScore);
    }
}
