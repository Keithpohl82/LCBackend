package com.launchcode.LCBackend;

import com.launchcode.LCBackend.service.ReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LcBackendApplication.class, args);
		ReportService service = new ReportService();
		service.pollReportApi();
	}

}
