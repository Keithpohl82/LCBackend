package com.launchcode.LCBackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ReportService {

    public void pollReportApi () {
        System.out.println("polling report api: " + LocalDateTime.now());
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity("http://localhost:8080/mock-api", String.class);
        System.out.println(response);
    }
}
