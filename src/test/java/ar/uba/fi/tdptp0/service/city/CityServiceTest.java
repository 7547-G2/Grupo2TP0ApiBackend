package ar.uba.fi.tdptp0.service.city;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {

    private CityService cityService = new CityService();

    CityServiceTest() throws IOException {
    }

    @Test
    void getCity() {
        cityService.getCity("RU");
    }
}