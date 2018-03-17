package ar.uba.fi.tdptp0.service.weather;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OfflineWeatherService implements WeatherService {

    private static final String FILE_NAME = "exampleResponse.json";
    private final String outputJson;

    public OfflineWeatherService() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        outputJson = new String(Files.readAllBytes(file.toPath()));
    }

    @Override
    public String forecastData(Long id) {
        return outputJson;

    }
}
