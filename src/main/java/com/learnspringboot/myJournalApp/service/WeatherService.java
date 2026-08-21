package com.learnspringboot.myJournalApp.service;

import com.learnspringboot.myJournalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey = "YOUR_API_KEY";
    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

//Get Weather Response via API-
    public WeatherResponse getWeather(String city) {

        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
// Note for above line -
        // in null (request entity) we can send any header, if needed.
        // WeatherResponse.class is written because we want received JSON Response to be converted in WeatherResponse POJO class.
        // process of converting JSON Response into corresponding java object (POJO) is called DeSerialization.

        WeatherResponse body = response.getBody();
        return body;
    }

//    Above is example of GET_Call from API, if we need POST_Call, check notes.txt for implementation.

}
