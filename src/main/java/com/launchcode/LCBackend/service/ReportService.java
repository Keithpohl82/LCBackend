package com.launchcode.LCBackend.service;

import com.launchcode.LCBackend.dto.responce.ReportApiResponce;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ReportService {

    public void pollReportApi () {
        System.out.println("polling report api: " + LocalDateTime.now());
        RestTemplate template = new RestTemplate();
        ResponseEntity<ReportApiResponce> response = template.getForEntity("http://localhost:8080/mock-api", ReportApiResponce.class);
        System.out.println(response.getBody());
    }
}
