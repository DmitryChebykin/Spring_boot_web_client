package com.example.admitad.service;

import com.example.admitad.service.scheduler.ActualTokenKeeper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProgramJsonReceiver {
    private final ActualTokenKeeper actualTokenKeeper;

    private final WebClient dataClient;

    @Value("${admitadDataUrl}")
    private String admitadDataUrl;

    public Optional<JSONObject> getJsonObject() {
        String authToken = actualTokenKeeper.getTokenData().getAuthToken();
        String jsonStr;
        JSONObject jsonObject;

        try {
            WebClient.ResponseSpec retrieve = dataClient.get().uri(admitadDataUrl).header("Authorization", "Bearer " + authToken).header("Cookie", "gdpr_country=0; section=webmaster").retrieve();

            Mono<String> mono = retrieve.bodyToMono(String.class);
            jsonStr = mono.block();
        } catch (WebClientRequestException e) {
            log.info("Ошибка соединения " + admitadDataUrl);
            log.info(String.valueOf(e));

            return Optional.empty();
        }

        jsonObject = new JSONObject(jsonStr);

        return Optional.of(jsonObject);
    }
}