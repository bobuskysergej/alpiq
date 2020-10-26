package com.alpiq.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {

    @Test
    public void getFullName() {
        City city1 = new City("name1", "country1");
        City city2 = new City("name2", "country2");

        assertThat(city1.getFullName()).isEqualTo("name1, country1");
        assertThat(city2.getFullName()).isEqualTo("name2, country2");
    }
}
