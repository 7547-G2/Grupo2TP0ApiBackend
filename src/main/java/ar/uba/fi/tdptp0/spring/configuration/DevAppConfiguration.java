package ar.uba.fi.tdptp0.spring.configuration;

import ar.uba.fi.tdptp0.service.weather.OfflineWeatherService;
import ar.uba.fi.tdptp0.service.weather.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevAppConfiguration {

    @Bean
    public WeatherService weatherService() {
        return new OfflineWeatherService();
    }
}
