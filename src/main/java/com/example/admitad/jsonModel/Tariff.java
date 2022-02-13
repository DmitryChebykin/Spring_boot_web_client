package com.example.admitad.jsonModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tariff {
    @JsonProperty("action_id")
    private Integer actionId;

    private Integer id;

    private String name;

    private List<Rate> rates;
}