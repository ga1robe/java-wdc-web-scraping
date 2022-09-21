package com.github.ga1robe.wdcwebscraping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.ga1robe.wdcwebscraping")
public class WdcWebScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WdcWebScrapingApplication.class, args);
	}

}
