package com.alpiq.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class City {

    private String name;
    private String country;

    public String getFullName() {
        return String.format("%s, %s", name, country);
    }
}
