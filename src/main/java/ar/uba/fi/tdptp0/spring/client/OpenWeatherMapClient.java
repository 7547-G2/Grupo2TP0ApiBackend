package ar.uba.fi.tdptp0.spring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-api", url = "https://api.openweathermap.org/data/2.5")
public interface OpenWeatherMapClient {

    @GetMapping(value = "/forecast?id={cityId}&appid=${app.api-key}", produces = {"application/json"})
    String getWeather(@PathVariable("cityId") long cityId);
}
