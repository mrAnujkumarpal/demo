package com.restTemplate.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {


    private String id;
    private CompanyDTO company;
    private AddressDTO address;


}
