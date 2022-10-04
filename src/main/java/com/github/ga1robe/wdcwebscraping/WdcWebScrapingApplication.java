package com.github.ga1robe.wdcwebscraping;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableAsync
@Controller
@ComponentScan("com.github.ga1robe.wdcwebscraping")
public class WdcWebScrapingApplication {

	public static void main(String[] args)  {
		SpringApplication.run(WdcWebScrapingApplication.class, args);
	}

}
