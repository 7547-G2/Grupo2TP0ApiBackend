package ar.uba.fi.tdptp0.service.weather;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OfflineWeatherService implements WeatherService {

    private static final Logger LOGGER = LogManager.getLogger(OfflineWeatherService.class);
    private static final String ERROR_MESSAGE = "Cant't open file";
    private static final String FILE_NAME = "exampleResponse.json";

    //TODO sent the file in the creator, so as to have the file already loaded forever and not each time it's called

    @Override
    public String forecastData(Long id) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        try {
            String outputJson = new String(Files.readAllBytes(file.toPath()));
            return  outputJson;
        } catch (IOException e) {
            LOGGER.error("Error while trying to open file: {}", FILE_NAME);
        }

        return ERROR_MESSAGE;
    }
}
