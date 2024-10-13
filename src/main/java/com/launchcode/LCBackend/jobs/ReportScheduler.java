package com.launchcode.LCBackend.jobs;

import com.launchcode.LCBackend.service.ReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportScheduler {

    private ReportService reportService;

    public ReportScheduler(ReportService reportService) {
        this.reportService = reportService;
    }

    //https://crontab.guru/
    @Scheduled(fixedDelay = 5000)
    public void pollReportApi () {
        reportService.pollReportApi();
    }
}
