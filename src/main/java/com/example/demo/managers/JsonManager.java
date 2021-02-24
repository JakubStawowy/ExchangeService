package com.example.demo.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.net.HttpURLConnection;
import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JsonManager {

    private final Map<String, Float> currencyMap;

    public JsonManager() throws IOException, ParseException {
        URL url = new URL("https://api.exchangeratesapi.io/latest");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.readLine();

        ObjectMapper objectMapper = new ObjectMapper();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        currencyMap = objectMapper.readValue(object.get("rates").toString(), new TypeReference<Map<String, Float>>() {});
        currencyMap.put((String) object.get("base"), 1.0f);

    }

    public Map<String, Float> getCurrencyMap() {
        return currencyMap;
    }

    public List<String> getKeys(){

        List<String> sortedList = new ArrayList<>(currencyMap.keySet());
        Collections.sort(sortedList);

        return sortedList;
    }
}
