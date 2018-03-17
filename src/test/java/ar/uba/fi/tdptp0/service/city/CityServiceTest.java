package ar.uba.fi.tdptp0.service.city;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class CityServiceTest {

    private CityService cityService = new CityService("shortCityList.json");

    CityServiceTest() throws IOException {
    }

    @Test
    void getCity() {
        String abc = cityService.getCity("RU");
    }
}