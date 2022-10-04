package com.github.ga1robe.wdcwebscraping;

import com.github.ga1robe.wdcwebscraping.model.CardContainer;
import com.github.ga1robe.wdcwebscraping.service.CardsService;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WdcWebScrapingApplicationTests {

	@Autowired
	CardsService cardsService;

	@Test
	void contextLoads() {
		//clear sample data
		cardsService.clear();
//		cardsService.addRecord(new TempRecord(0L, LocalDate.now(), LocalTime.of(10, 0),"Kraków",10));
		cardsService.addRecord(new CardContainer("Retro męski naszyjnik", 20.49, "zł", "ZXWXZ Factory Store", "Darmowa dostawa", 37, ""));
		cardsService.addRecord(new CardContainer("Pistolet do masażu", 219.52, "zł", "HEY Hiphop Jewelry Store", "Darmowa dostawa", 3, ""));
		cardsService.addRecord(new CardContainer("Dropship Center Man High Quality", 61.99, "zł", "Anya Anime Store", "Darmowa dostawa", 0, ""));
//		assertEquals(15, cardsService.getCityStatistics(LocalDate.now(),"Kraków").getAvgTemp());

	}

}
