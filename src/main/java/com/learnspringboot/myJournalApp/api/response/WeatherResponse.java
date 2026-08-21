package com.learnspringboot.myJournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current {

        private int temperature;
        private int feelslike;

        @JsonProperty("weather_descriptions") // Used because we have changed the name of below attribute from "snake_case" to "camelCase" i.e. (weather_descriptions) to (weatherDescriptions)
        private ArrayList<String> weatherDescriptions;

    }
}