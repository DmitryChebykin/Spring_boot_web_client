package com.example.admitad.service;

import com.example.admitad.jsonModel.JsonActionDetail;
import com.example.admitad.jsonModel.AdvertisementProgram;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;
import com.example.admitad.myBatisPlus.domain.Program;
import com.example.admitad.myBatisPlus.service.ServiceCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {
    private final ServiceCollection serviceCollection;
    private final ObjectMapper objectMapper;
    private final WebClient dataClient;


    public void saveJsonToDB(JSONObject jsonObject) {
        log.info("Сохраняю json");
        log.info(jsonObject.toString());

        JSONArray results = jsonObject.getJSONArray("results");

        List<JSONObject> jsonObjectList = StreamSupport.stream(results.spliterator(), false)
                .map(e -> (JSONObject) e)
                .collect(Collectors.toList());


        List<AdvertisementProgram> advertisementProgramList = jsonObjectList.stream()
                .map(e -> getOptionalObjFromJsonString(e.toString(), AdvertisementProgram.class)).map(Optional::get)
                .collect(Collectors.toList());


        List<Program> programList = advertisementProgramList.stream().map(e -> {
            Program program = new Program();
            BeanUtils.copyProperties(e, program);
            byte[] imageByte = getBytesFromUri(e.getImageUri());
            program.setImage(imageByte);
            return program;
        }).collect(Collectors.toList());

        serviceCollection.getProgramService().batchInsertOrUpdate(programList);

        List<JsonActionDetail> jsonActionDetailList = advertisementProgramList.stream().flatMap(e -> e.getActionsDetail().stream()).collect(Collectors.toList());


        List<ActionsDetail> actionsDetailList = jsonActionDetailList.stream().map(e -> {
            ActionsDetail actionsDetail = new ActionsDetail();
            BeanUtils.copyProperties(e, actionsDetail);
            return actionsDetail;
        }).collect(Collectors.toList());


        serviceCollection.getActionsDetailService().batchInsertOrUpdate(actionsDetailList);


//        IntStream.range(0, results.length()).forEach(e -> {
//            try {
//
//
//                String content = results.get(e).toString();
//
//                AdvertisementProgram advertisementProgram = objectMapper.readValue(content, AdvertisementProgram.class);
//
//                Program target = new Program();
//                BeanUtils.copyProperties(advertisementProgram, target);
//
//                serviceCollection.getProgramService().insertOrUpdate(target);
//
//                advertisementProgram.getActionsDetail();
//
//                List<Program> list = programService.list();
//
//
//                log.info(advertisementProgram.toString());
//
//                saveOrUpdateActionsDetailList(advertisementProgram);
//
//                saveOrUpdateCategoryList(advertisementProgram);
//
//                saveOrUpdateProgram(advertisementProgram);
//
//            } catch (JsonProcessingException | JSONException ex) {
//                ex.printStackTrace();
//            }
//        });
    }

    private byte[] getBytesFromUri(String dataUri) {
        WebClient.ResponseSpec retrieve = dataClient.get().uri(dataUri).retrieve();
        return retrieve.bodyToMono(byte[].class).block();
    }

    private <T> Optional<T> getOptionalObjFromJsonString(String json, Class<T> contentClass) {
        try {
            return Optional.ofNullable(objectMapper.readValue(json, contentClass));
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return Optional.empty();
    }


//    private void saveOrUpdateProgram(AdvertisementProgram advertisementProgram) {
//        String imageUri = advertisementProgram.getImageUri();
//
//        if (imageUri != null) {
//            WebClient.ResponseSpec retrieve = dataClient.get().uri(imageUri).retrieve();
//            byte[] imageByte = retrieve.bodyToMono(byte[].class).block();
//            advertisementProgram.setImage(imageByte);
//        }
//
//        log.info("save program");
//
//        programRepository.insertOrUpdateItem(advertisementProgram);
//    }
//
//    private void saveOrUpdateCategoryList(AdvertisementProgram advertisementProgram) {
//        log.info("save category");
//
//        advertisementProgram.getCategories().forEach(categoryRepository::insertOrUpdateItem);
//    }
//
//    private void saveOrUpdateActionsDetailList(AdvertisementProgram advertisementProgram) {
//        log.info("save actions_detail");
//
//        advertisementProgram.getActionsDetail().forEach(actionsDetailRepository::insertOrUpdateItem);
//    }
}