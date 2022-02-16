package com.example.admitad.service;

import com.example.admitad.myBatisPlus.domain.Program;
import com.example.admitad.myBatisPlus.service.ProgramService;
import com.example.admitad.jsonModel.AdvertisementProgram;
import com.example.admitad.repository.ActionsDetailRepository;
import com.example.admitad.repository.CategoryRepository;
import com.example.admitad.repository.ProgramRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;
    private final ActionsDetailRepository actionsDetailRepository;
    private final WebClient dataClient;
    private final ProgramService programService;

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

                    Program target = new Program();
                    BeanUtils.copyProperties(advertisementProgram, target);

                    programService.insertOrUpdate(target);
                    List<Program> list = programService.list();


                    log.info(advertisementProgram.toString());

                    saveOrUpdateActionsDetailList(advertisementProgram);

                    saveOrUpdateCategoryList(advertisementProgram);

                    saveOrUpdateProgram(advertisementProgram);

                } catch (JsonProcessingException | JSONException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveOrUpdateProgram(AdvertisementProgram advertisementProgram) {
        String imageUri = advertisementProgram.getImageUri();

        if (imageUri != null) {
            WebClient.ResponseSpec retrieve = dataClient.get().uri(imageUri).retrieve();
            byte[] imageByte = retrieve.bodyToMono(byte[].class).block();
            advertisementProgram.setImage(imageByte);
        }

        log.info("save program");

        programRepository.insertOrUpdateItem(advertisementProgram);
    }

    private void saveOrUpdateCategoryList(AdvertisementProgram advertisementProgram) {
        log.info("save category");

        advertisementProgram.getCategories().forEach(categoryRepository::insertOrUpdateItem);
    }

    private void saveOrUpdateActionsDetailList(AdvertisementProgram advertisementProgram) {
        log.info("save actions_detail");

        advertisementProgram.getActionsDetail().forEach(actionsDetailRepository::insertOrUpdateItem);
    }
}