package com.restTemplate.demo.controller;

import com.restTemplate.demo.domain.Company;
import com.restTemplate.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping("all")
    public ResponseEntity<List<Company>> allCompany() {
        return ResponseEntity.ok(this.companyService.allCampanies());
    }
}
