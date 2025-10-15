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

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;

@Service
@Slf4j
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;
    
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
    // Get template URL from cache
    String templateAPI = appCache.aapCache.get(AppCache.keys.WEATHER_API.toString());

    // Replace placeholders safely using replace (not replaceAll)
    String finalAPI = templateAPI
            .replace(Placeholders.API_KEY, apiKey)   // <apiKey> → actual API key
            .replace(Placeholders.CITY, city);      // <city> → city name

    // Call API
    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null,
            WeatherResponse.class);

    return response.getBody();
}

    // How to Consume External POST APIs Effectively

    //    HttpHeaders httpHeaders = new HttpHeaders();
    //    httpHeaders.set("key":"value");
    //    User user= User.builder().username("mohan").password("mohan").build();
    //    HttpEntity<User> httpEntity=new HttpEntity<>(user,httpHeaders);
    //     ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity,
    //             WeatherResponse.class);
}
