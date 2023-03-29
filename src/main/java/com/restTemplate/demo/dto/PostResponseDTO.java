package com.restTemplate.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostResponseDTO {

    @JsonProperty(required = true)
    private String userId;

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private String body;


}
