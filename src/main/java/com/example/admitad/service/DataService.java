package com.example.admitad.service;

import com.example.admitad.jsonModel.AdvertisementProgram;
import com.example.admitad.repository.CategoryRepository;
import com.example.admitad.repository.ProgramRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;
    private final WebClient dataClient;

    @Transactional
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

                    advertisementProgram.getCategories().forEach(categoryRepository::insertOrUpdateItem);

                    String imageUri = advertisementProgram.getImageUri();

                    if(imageUri != null){
                        WebClient.ResponseSpec retrieve = dataClient.get().uri(imageUri).retrieve();
                        byte[] imageByte = retrieve.bodyToMono(byte[].class).block();
                        advertisementProgram.setImageBytes(imageByte);
                    }

                    programRepository.insertOrUpdateItem(advertisementProgram);

                    String asString = objectMapper.writeValueAsString(advertisementProgram);

                    log.info(asString);
                } catch (JsonProcessingException | JSONException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}