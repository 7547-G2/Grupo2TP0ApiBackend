package ar.uba.fi.tdptp0.service.weather;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class OfflineWeatherService implements WeatherService {

    private static final String FILE_NAME = "exampleResponse.json";
    private final String outputJson;

    public OfflineWeatherService() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream(FILE_NAME);
        outputJson = IOUtils.toString(inputStream, "UTF-8");
    }

    @Override
    public String forecastData(Long id) {
        return outputJson;

    }
}
