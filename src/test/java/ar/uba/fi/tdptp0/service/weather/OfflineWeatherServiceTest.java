package ar.uba.fi.tdptp0.service.weather;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class OfflineWeatherServiceTest {

    private WeatherService offlineWeatherService = new OfflineWeatherService();

    OfflineWeatherServiceTest() throws IOException {
    }

    @Test
    void offlineWeatherServiceWorks() {
        offlineWeatherService.forecastData(1L);
    }
}