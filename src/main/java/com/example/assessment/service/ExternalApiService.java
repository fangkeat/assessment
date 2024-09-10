package com.example.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    @Autowired
    private static RestTemplate restTemplate;

    @Value("${url.external}")
    private String externalUrl;

    public String externalUrlCall() {
        if (restTemplate == null) {
            throw new IllegalStateException("RestTemplate has not been initialized");
        }
        return restTemplate.getForEntity(externalUrl, String.class).getBody();
    }



}
