package com.restTemplate.demo.service;

import com.restTemplate.demo.domain.Company;
import com.restTemplate.demo.dto.CompanyDTO;
import com.restTemplate.demo.dto.GeoDTO;
import com.restTemplate.demo.dto.PostResponseDTO;
import com.restTemplate.demo.dto.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Value("${app.userURL}")
    private String userURL;

    @Value("${app.postsURL}")
    private String postsURL;


    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Company> allCampanies() {
        Map<String, UserResponseDTO> userRespMap = getUserDataFromAPI();
        Map<String, PostResponseDTO> postRespMap = getPostDataFromAPI();
        return filteredDataFromUserAndPost(userRespMap, postRespMap);
    }

    private List<Company> filteredDataFromUserAndPost(Map<String, UserResponseDTO> userRespMap, Map<String, PostResponseDTO> postRespMap) {
        List<Company> companyList = new ArrayList<>();
        Set<String> postSet = postRespMap.keySet();
        for (Map.Entry m : userRespMap.entrySet()) {
            String key = (String) m.getKey();
            if (postSet.contains(key)) {
                PostResponseDTO postResponseDTO = postRespMap.get(key);
                UserResponseDTO userResponseDTO = (UserResponseDTO) m.getValue();


                Company company = new Company();

                company.setId(key);
                company.setTitle(postResponseDTO.getTitle());
                company.setBody(postResponseDTO.getBody());

                CompanyDTO companyDTO = userResponseDTO.getCompany();
                company.setCompanyName(companyDTO.getName());


                GeoDTO geoDTO = userResponseDTO.getAddress().getGeo();
                company.setLng(geoDTO.getLng());
                company.setLat(geoDTO.getLat());

                companyList.add(company);
            }
        }
        return companyList;
    }

    private Map<String, PostResponseDTO> getPostDataFromAPI() {
        PostResponseDTO[] postResp = restTemplate.getForObject(postsURL, PostResponseDTO[].class);
        Map<String, PostResponseDTO> postRespMap = new HashMap<>();
        for (PostResponseDTO p : postResp) {
            postRespMap.put(p.getId(), p);
        }
        return postRespMap;
    }

    private Map<String, UserResponseDTO> getUserDataFromAPI() {
        UserResponseDTO[] userResp = restTemplate.getForObject(userURL, UserResponseDTO[].class);
        Map<String, UserResponseDTO> userRespMap = new HashMap<>();
        for (UserResponseDTO u : userResp) {
            userRespMap.put(u.getId(), u);
        }
        return userRespMap;
    }


}
