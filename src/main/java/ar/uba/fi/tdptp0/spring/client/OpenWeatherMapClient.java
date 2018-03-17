package ar.uba.fi.tdptp0.spring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-api", url = "https://api.openweathermap.org/data/2.5")
public interface OpenWeatherMapClient {

    @GetMapping(value = "/forecast?id={cityId}&appid=b14c689324c9d7fa0e17735d6fe38087", produces = {"application/json"})
    String getWeather(@PathVariable("cityId") long cityId);
}
