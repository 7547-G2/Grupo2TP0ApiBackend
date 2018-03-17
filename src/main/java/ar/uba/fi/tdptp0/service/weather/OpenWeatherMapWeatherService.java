package ar.uba.fi.tdptp0.service.weather;

import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.springframework.beans.factory.annotation.Autowired;

public class OpenWeatherMapWeatherService implements WeatherService {

    OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    public OpenWeatherMapWeatherService(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    @Override
    public String forecastData(Long id) {
        return openWeatherMapClient.getWeather(id);
    }
}
