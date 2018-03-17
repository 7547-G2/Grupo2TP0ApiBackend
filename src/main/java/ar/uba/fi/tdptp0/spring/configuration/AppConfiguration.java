package ar.uba.fi.tdptp0.spring.configuration;

import ar.uba.fi.tdptp0.service.weather.OpenWeatherMapWeatherService;
import ar.uba.fi.tdptp0.service.weather.WeatherService;
import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public WeatherService weatherService(OpenWeatherMapClient openWeatherMapClient) {
        return new OpenWeatherMapWeatherService(openWeatherMapClient);
    }
}
