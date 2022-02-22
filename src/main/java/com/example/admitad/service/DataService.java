package com.example.admitad.service;

import com.example.admitad.jsonModel.JsonAdvertisementProgram;
import com.example.admitad.jsonModel.JsonActionDetail;
import com.example.admitad.jsonModel.JsonTariff;
import com.example.admitad.myBatisPlus.domain.ActionsDetail;
import com.example.admitad.myBatisPlus.domain.Program;
import com.example.admitad.myBatisPlus.domain.Tariff;
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

import java.util.*;
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
                .map(JSONObject.class::cast)
                .collect(Collectors.toList());


        List<JsonAdvertisementProgram> jsonAdvertisementProgramList = jsonObjectList.stream()
                .map(e -> getOptionalObjFromJsonString(e.toString(), JsonAdvertisementProgram.class)).map(Optional::get)
                .collect(Collectors.toList());

        List<Program> programList = jsonAdvertisementProgramList.stream().map(this::getProgram).collect(Collectors.toList());

        serviceCollection.getProgramService().batchInsertOrUpdate(programList);

        List<JsonActionDetail> jsonActionDetailList = jsonAdvertisementProgramList.stream().flatMap(e -> e.getActionsDetail().stream()).collect(Collectors.toList());

        HashMap<ActionsDetail, List<Tariff>> actionsDetailListHashMap = getActionsDetailListHashMap(jsonActionDetailList);

        serviceCollection.getActionsDetailService().batchInsertOrUpdate(new ArrayList<>(actionsDetailListHashMap.keySet()));

        List<Tariff> tariffs = (actionsDetailListHashMap.values().stream().flatMap(Collection::stream)).collect(Collectors.toList());

        serviceCollection.getTariffService().batchInsertOrUpdate(tariffs);
    }

    private HashMap<ActionsDetail, List<Tariff>> getActionsDetailListHashMap(List<JsonActionDetail> jsonActionDetailList) {
        HashMap<ActionsDetail, List<Tariff>> actionsDetailListHashMap = new HashMap<>();

        jsonActionDetailList.forEach(e -> {
            ActionsDetail actionsDetail = new ActionsDetail();
            BeanUtils.copyProperties(e, actionsDetail);
            List<Tariff> tariffList = e.getJsonTariffs().stream()
                    .map(this::getTariff).collect(Collectors.toList());
            actionsDetailListHashMap.put(actionsDetail, tariffList);
        });
        return actionsDetailListHashMap;
    }

    private Program getProgram(JsonAdvertisementProgram e) {
        Program program = new Program();
        BeanUtils.copyProperties(e, program);
        byte[] imageByte = getBytesFromUri(e.getImageUri());
        program.setImage(imageByte);
        return program;
    }

    private Tariff getTariff(JsonTariff t) {
        Tariff tariff = new Tariff();
        BeanUtils.copyProperties(t, tariff);
        return tariff;
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
}