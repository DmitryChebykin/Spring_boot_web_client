package com.example.admitad.jsonModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementProgram {
    private String name;

    @JsonProperty("actions_detail")
    private List<ActionDetail> actionsDetail;

    @JsonProperty("image")
    private String imageUri;

    @JsonProperty("products_xml_link")
    private String productXmlLink;

    @JsonProperty("categories")
    private List<Category> categories;

    @JsonProperty("gotolink")
    private String gotoLink;
}