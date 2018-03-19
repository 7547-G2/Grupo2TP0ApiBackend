package ar.uba.fi.tdptp0.service.weather;

import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpenWeatherMapWeatherService implements WeatherService {

    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    public OpenWeatherMapWeatherService(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    @Override
    public String forecastData(Long id) {
        String jsonString = openWeatherMapClient.getWeather(id);
        String parsedJson = parseResponse(jsonString);

        return parsedJson;
    }

    private String parseResponse(String jsonString) {
        JSONParser parser = new JSONParser();
        JSONObject weatherJson;
        try {
            weatherJson = (JSONObject) parser.parse(jsonString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "{}";
        }
        JSONObject responseJson = new JSONObject();
        responseJson = setCityDetails(responseJson, weatherJson);
        responseJson = setWeatherInfo(responseJson, weatherJson);

        return responseJson.toString();
    }

    private JSONObject setWeatherInfo(JSONObject responseJson, JSONObject weatherJson) {
        JSONArray listOfDays = (JSONArray) weatherJson.get("list");
        JSONArray listOf5Days = new JSONArray();
        Iterator listOfDaysIterator = listOfDays.iterator();
        JSONObject currentDay;
        currentDay = (JSONObject) listOfDaysIterator.next();
        for (int days = 1; days <= 5; days++) {
            JSONObject dayWeatherObject = new JSONObject();
            String dateFromCurrentDay = currentDateFromDay(currentDay);
            dayWeatherObject.put("date", dateFromCurrentDay);
            WeatherValueData dayWeatherValueData = new WeatherValueData();
            while (listOfDaysIterator.hasNext() && shouldAddDay(currentDay)) {
                dayWeatherValueData = addDataToWeatherValueData(dayWeatherValueData, currentDay);
                currentDay = (JSONObject) listOfDaysIterator.next();
            }
            WeatherValueData nightWeatherValueData = new WeatherValueData();
            while (listOfDaysIterator.hasNext() && shouldAddNight(currentDay)) {
                nightWeatherValueData = addDataToWeatherValueData(nightWeatherValueData, currentDay);
                currentDay = (JSONObject) listOfDaysIterator.next();
            }

            dayWeatherObject = weatherValueData(dayWeatherObject, dayWeatherValueData, "dayTemp", "dayIcon");
            dayWeatherObject = weatherValueData(dayWeatherObject, nightWeatherValueData, "nightTemp", "nightIcon");

            listOf5Days.add(dayWeatherObject);
        }

        responseJson.put("info", listOf5Days);

        return responseJson;
    }

    private WeatherValueData addDataToWeatherValueData(WeatherValueData weatherValueData, JSONObject currentDay) {
        JSONObject mainData = (JSONObject) currentDay.get("main");
        weatherValueData.temperatures.add(Double.parseDouble(mainData.get("temp").toString()) - 273.15);

        JSONObject weatherData = (JSONObject) ((JSONArray) currentDay.get("weather")).get(0);
        String dayIcon = weatherData.get("icon").toString();
        weatherValueData.icons.add(dayIcon);

        return weatherValueData;
    }

    private JSONObject weatherValueData(JSONObject dayWeatherObject, WeatherValueData weatherValueData, String typeOfTemp, String typeOfIcon) {
        if (weatherValueData.temperatures.isEmpty()) {
            return dayWeatherObject;
        }
        double totalTemperatures = weatherValueData.temperatures.size();
        Iterator temperatureIterator = weatherValueData.temperatures.iterator();
        double sumOfTemperatures = 0;
        while (temperatureIterator.hasNext()) {
            sumOfTemperatures += (Double) temperatureIterator.next();
        }
        double meanValue = sumOfTemperatures / totalTemperatures;
        dayWeatherObject.put(typeOfTemp, meanValue);
        dayWeatherObject.put(typeOfIcon, weatherValueData.icons.get(0));

        return dayWeatherObject;
    }

    private boolean shouldAddNight(JSONObject currentDay) {
        String input = currentDay.get("dt_txt").toString();
        String[] elements = input.split(" ");
        String hour = elements[1];
        return (hour.equals("12:00:00")
                || hour.equals("15:00:00")
                || hour.equals("18:00:00")
                || hour.equals("21:00:00")
                || hour.equals("00:00:00"));
    }

    private boolean shouldAddDay(JSONObject currentDay) {
        String input = currentDay.get("dt_txt").toString();
        String[] elements = input.split(" ");
        String hour = elements[1];
        return (hour.equals("00:00:00")
                || hour.equals("03:00:00")
                || hour.equals("06:00:00")
                || hour.equals("09:00:00")
                || hour.equals("12:00:00"));
    }

    private String currentDateFromDay(JSONObject currentDay) {
        String input = currentDay.get("dt_txt").toString();
        String[] elements = input.split(" ");
        return elements[0];
    }

    private JSONObject setCityDetails(JSONObject responseJson, JSONObject weatherJson) {
        responseJson.put("id", ((JSONObject) weatherJson.get("city")).get("id"));
        responseJson.put("name", ((JSONObject) weatherJson.get("city")).get("name"));
        responseJson.put("country", ((JSONObject) weatherJson.get("city")).get("country"));

        return responseJson;
    }

    public class WeatherValueData {
        List<Double> temperatures = new ArrayList<>();
        List<String> icons = new ArrayList<>();
    }

}
