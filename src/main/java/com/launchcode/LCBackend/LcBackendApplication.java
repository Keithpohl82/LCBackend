package com.launchcode.LCBackend;

import com.launchcode.LCBackend.service.ReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LcBackendApplication.class, args);

	}

}
