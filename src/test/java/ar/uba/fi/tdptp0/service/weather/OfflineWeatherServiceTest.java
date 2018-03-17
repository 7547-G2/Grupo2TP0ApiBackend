package ar.uba.fi.tdptp0.service.weather;

import org.junit.jupiter.api.Test;

class OfflineWeatherServiceTest {

    private WeatherService offlineWeatherService = new OfflineWeatherService();

    @Test
    void offlineWeatherServiceWorks() {
        offlineWeatherService.forecastData(1L);
    }
}