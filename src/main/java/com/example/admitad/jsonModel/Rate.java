package com.example.admitad.jsonModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @JsonProperty("price_s")

    private String priceS;

    private String size;

    @JsonProperty("tariff_id")
    private Integer tariffId;

    private String country;

    @JsonProperty("date_s")
    private String dateS;

    @JsonProperty("is_percentage")
    private Boolean isPercentage;

    private Integer id;
}
