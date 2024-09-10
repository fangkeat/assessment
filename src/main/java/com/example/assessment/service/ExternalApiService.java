package com.example.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExternalApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.external}")
    private String externalUrl;

    public String externalUrlCall() {
        Optional.ofNullable(restTemplate).orElseThrow( () -> new IllegalStateException("RestTemplate has not been initialized"));

        Optional.ofNullable(externalUrl).orElseThrow( () -> new NoSuchElementException("External Url not found"));

        return restTemplate.getForEntity(externalUrl, String.class).getBody();
    }



}
