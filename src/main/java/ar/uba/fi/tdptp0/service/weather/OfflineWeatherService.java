package ar.uba.fi.tdptp0.service.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OfflineWeatherService implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineWeatherService.class);
    private static final String FILE_NAME = "exampleResponse.json";
    private final String outputJson;

    public OfflineWeatherService() throws IOException {
        LOGGER.info("Trying to load WeatherService with filename: {}", FILE_NAME);
        File file = new File(ClassLoader.getSystemResource(FILE_NAME).getPath());
        LOGGER.info("Now I have the file with path: {}", file.toPath());
        outputJson = new String(Files.readAllBytes(file.toPath()));
    }

    @Override
    public String forecastData(Long id) {
        return outputJson;

    }
}
