/*
package ar.uba.fi.tdptp0.spring.configuration;

import ar.uba.fi.tdptp0.service.weather.OpenWeatherMapWeatherService;
import ar.uba.fi.tdptp0.service.weather.WeatherService;
import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdAppConfiguration {

    @Bean
    public WeatherService weatherService(OpenWeatherMapClient openWeatherMapClient) {
        return new OpenWeatherMapWeatherService(openWeatherMapClient);
    }
}
*/
