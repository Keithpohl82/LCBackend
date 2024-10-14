package com.launchcode.LCBackend.service;

import com.launchcode.LCBackend.dto.MatchDto;
import com.launchcode.LCBackend.dto.responce.ReportApiResponce;
import com.launchcode.LCBackend.entity.Match;
import com.launchcode.LCBackend.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        LocalDate now = LocalDate.now();
        LocalDate twoDaysAgo = now.minusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        String dateStart = formatter.format(now);
        String dateEnd = formatter.format(twoDaysAgo);

        ResponseEntity<ReportApiResponce> response = template.getForEntity("http://localhost:8080/mock-api?page=1&per_page=1000&Date_start="+dateStart+"&date_end="+dateEnd, ReportApiResponce.class);
        ReportApiResponce data = response.getBody();

        if(data != null) {
            for (MatchDto apiMatch : data.matches()) {
                String m = """
                            Full Name: %s\s
                            Email Address: %s\s
                            Phone Number %s\s""";

                final String message = String.format(m, apiMatch.fullName(), apiMatch.email(), apiMatch.phoneNumber());

                Optional<Match> dbMatchOpt = matchRepository.findByEmail(apiMatch.email());
                dbMatchOpt.ifPresentOrElse(dbMatch -> {
                    if(!dbMatch.getCreatedAt().equals(apiMatch.createdAt())){
                        System.out.println("Send a message to slack for match: " + dbMatch);
                        dbMatch.setCreatedAt(apiMatch.createdAt());
                        matchRepository.save(dbMatch);
                        SlackBot.postMessage("User has updated \n" +message);
                        DiscordBot.postMessage("User has updated \n" +message);
                    }

                    }, () -> {
                    System.out.println("Not Match Found");
                    Match matchToAdd = new Match(apiMatch.email(), apiMatch.createdAt(), apiMatch.fullName(), apiMatch.phoneNumber());
                    matchRepository.save(matchToAdd);

                    DiscordBot.postMessage(message);
                    SlackBot.postMessage(message);
                });
            }
        }
    }
}
