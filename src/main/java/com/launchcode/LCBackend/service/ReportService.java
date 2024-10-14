package com.launchcode.LCBackend.service;

import com.launchcode.LCBackend.dto.MatchDto;
import com.launchcode.LCBackend.dto.responce.ReportApiResponce;
import com.launchcode.LCBackend.entity.Match;
import com.launchcode.LCBackend.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReportService {

    private MatchRepository matchRepository;

    public ReportService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void pollReportApi () {
        System.out.println("polling report api: " + LocalDateTime.now());
        RestTemplate template = new RestTemplate();
        ResponseEntity<ReportApiResponce> response = template.getForEntity("http://localhost:8080/mock-api", ReportApiResponce.class);
        ReportApiResponce data = response.getBody();

        if(data != null) {
            for (MatchDto apiMatch : data.matches()) {
                Optional<Match> dbMatchOpt = matchRepository.findByEmail(apiMatch.email());
                dbMatchOpt.ifPresentOrElse(dbMatch -> {
                    if(!dbMatch.getCreatedAt().equals(apiMatch.createdAt())){
                        System.out.println("Send a message to slack for match: " + dbMatch);
                        dbMatch.setCreatedAt(apiMatch.createdAt());
                        matchRepository.save(dbMatch);
                    }

                    }, () -> {
                    System.out.println("Not Match Found");
                    Match matchToAdd = new Match(apiMatch.email(), apiMatch.createdAt(), apiMatch.fullName(), apiMatch.phoneNumber());
                    matchRepository.save(matchToAdd);
                });
            }
        }
    }
}
