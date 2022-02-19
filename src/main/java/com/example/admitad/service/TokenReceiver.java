package com.example.admitad.service;

import com.example.admitad.tokenModel.TokenData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
@PropertySource("classpath:account.properties")
public class TokenReceiver {
    public static final String ACCESS_TOKEN = "access_token";

    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String EXPIRES_IN = "expires_in";

    private final WebClient tokenClient;

    @Value("${admitadTokenUrl}")
    private String admitadTokenUrl;

    @Value("${clientId}")
    private String clientId;

    @Value("${basicAuthKey}")
    private String basicAuthKey;

    @Value("${clientSecret}")
    private String clientSecret;

    public TokenData getTokenData() {
        MultiValueMap<String, String> formData = getNewRefreshTokenMap();
        String jsonStr;

        try {
            WebClient.ResponseSpec retrieve = tokenClient.post().uri(admitadTokenUrl).header("Authorization", "Basic " + basicAuthKey).header("Content-Type", "application/x-www-form-urlencoded").header("Cookie", "gdpr_country=0; section=webmaster; gdpr_country=0; section=webmaster; gdpr_country=0; section=webmaster").body(BodyInserters.fromFormData(formData)).retrieve();

            Mono<String> mono = retrieve.bodyToMono(String.class);
            jsonStr = mono.block();
        } catch (WebClientRequestException e) {
            log.info("Ошибка соединения " + admitadTokenUrl);
            log.info(String.valueOf(e));
            return TokenData.builder().build();
        }

        return getTokenData(jsonStr);
    }

    private TokenData getTokenData(String jsonStr) {
        TokenData tokenData = TokenData.builder().build();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);

            String authToken = (String) jsonObject.get(ACCESS_TOKEN);
            log.info("Auth token: " + authToken);

            String refreshToken = (String) jsonObject.get(REFRESH_TOKEN);
            log.info("Refresh token: " + refreshToken);

            tokenData = TokenData.builder().authToken(authToken).refreshToken(refreshToken).expiresIn((Integer) jsonObject.get(EXPIRES_IN)).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tokenData.setReceivedDate(new Date());

        return tokenData;
    }

    private MultiValueMap<String, String> getNewRefreshTokenMap() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("scope", "advcampaigns websites public_data advcampaigns_for_website");
        return formData;
    }

    public TokenData refreshTokenData(String refreshToken) {
        MultiValueMap<String, String> formData = getActualAuthTokenMap(refreshToken);

        WebClient.ResponseSpec retrieve = tokenClient.post().header("Content-Type", "application/x-www-form-urlencoded").header("Cookie", "gdpr_country=0; section=webmaster").body(BodyInserters.fromFormData(formData)).retrieve();

        Mono<String> mono = retrieve.bodyToMono(String.class);
        String jsonStr = mono.block();

        return getTokenData(jsonStr);
    }

    private MultiValueMap<String, String> getActualAuthTokenMap(String refreshToken) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", REFRESH_TOKEN);
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add(REFRESH_TOKEN, refreshToken);
        return formData;
    }
}