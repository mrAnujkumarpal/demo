package com.restTemplate.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company {

    private String id;
    private String title;
    private String body;
    private String lat;
    private String lng;
    private String companyName;

}
