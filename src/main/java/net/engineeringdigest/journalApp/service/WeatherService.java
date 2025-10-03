package net.engineeringdigest.journalApp.service;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    private static final String API = "http://api.weatherstack.com/current ? access_key = API_KEY & query = CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalAPI = API.replaceAll("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null,
                WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
        // How to Consume External POST APIs Effectively

    //    HttpHeaders httpHeaders = new HttpHeaders();
    //    httpHeaders.set("key":"value");
    //    User user= User.builder().username("mohan").password("mohan").build();
    //    HttpEntity<User> httpEntity=new HttpEntity<>(user,httpHeaders);
    //     ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity,
    //             WeatherResponse.class);

    }
}
