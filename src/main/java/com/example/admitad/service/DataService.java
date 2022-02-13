package com.example.admitad.service;

import com.example.admitad.jsonModel.AdvertisementProgram;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@Slf4j
public class DataService {
    public void saveJsonToDB(JSONObject jsonObject) {
        log.info("Сохраняю json");
        log.info(jsonObject.toString());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JSONArray results = jsonObject.getJSONArray("results");

            IntStream.range(0, results.length()).forEach(e -> {
                        try {
                            String content = results.get(e).toString();

                            AdvertisementProgram advertisementProgram = objectMapper.readValue(content, AdvertisementProgram.class);

                            log.info(advertisementProgram.toString());

                            String asString = objectMapper.writeValueAsString(advertisementProgram);

                            log.info(asString);
                        } catch (JsonProcessingException | JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}