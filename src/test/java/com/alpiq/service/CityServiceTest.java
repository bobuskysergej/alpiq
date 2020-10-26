package com.alpiq.service;

import com.alpiq.domain.City;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityServiceTest {

    private CityService cityService;

    @Before
    public void setUp() throws Exception {
        cityService = new CityService();
    }

    @Test
    public void getCities() {
        assertThat(cityService.getCities()).hasSize(24540);
        City city = cityService.getCities().get(0);
        assertThat(city.getName()).isEqualTo("les Escaldes");
        assertThat(city.getCountry()).isEqualTo("Europe/Andorra");
        assertThat(city.getFullName()).isEqualTo("les Escaldes, Europe/Andorra");
    }
}
