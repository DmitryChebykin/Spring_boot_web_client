package com.example.admitad.jsonModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class JsonAdvertisementProgram {
    private String name;

    @JsonProperty("actions_detail")
    private List<JsonActionDetail> actionsDetail;

    @JsonProperty("image")
    private String imageUri;

    @JsonIgnore
    private byte[] image;

    @JsonProperty("products_xml_link")
    private String productXmlLink;

    @JsonProperty("categories")
    private List<JsonCategory> categories;

    @JsonProperty("gotolink")
    private String gotoLink;

    @JsonProperty("id")
    private Integer id;
}