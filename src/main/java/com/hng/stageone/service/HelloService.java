package com.hng.stageone.service;


import com.hng.stageone.model.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HelloService {

    private  final RestTemplate restTemplate = new RestTemplate();

    public ResponseModel getGreeting(String visitorName, String client_ip){
        String location = getLocation(client_ip);
        String temperature = getTemperature(location);

        return ResponseModel.builder().client_ip(client_ip).location(location).greeting(String.format("Hello, %s!, the temperature is %s degrees Celcius in %s", visitorName.replace("\"", ""), temperature, location)).build();
    }

    private  String getLocation(String ip){

        String url = String.format("http://ip-api.com/json%s", ip);
        Map<String, String> response = restTemplate.getForObject(url, Map.class);

        return response != null ? response.get("city") : "Unknown";
    }

    private  String getTemperature(String city){
        String apikey = "54ce1a996d5a72bf66e966b22d069e9d";
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apikey);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> main = response != null ? (Map<String, Object>) response.get("main") : null;

        return main != null ? String.valueOf(main.get("temp")) : "Unknown";
    }
}
