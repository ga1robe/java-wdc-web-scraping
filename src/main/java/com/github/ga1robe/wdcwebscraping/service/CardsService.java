package com.github.ga1robe.wdcwebscraping.service;

import javax.annotation.PostConstruct;

import com.github.ga1robe.wdcwebscraping.model.CardContainer;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CardsService {

    private static List<CardContainer> records = new ArrayList<CardContainer>();
    private static String searchTitle = new String();

    @PostConstruct
    public void loadData() {
        clear();
        System.out.println("[loadData] Adding data on startup...");
        DynamicScraping();
    }

    private void DynamicScraping() {
        try (Playwright playwright = Playwright.create()) {
            final BrowserType chromium = playwright.chromium();
            final Browser browser = chromium.launch();
            final Page page = browser.newPage();
            page.navigate("https://pl.aliexpress.com/w/wholesale-dropshipping-center.html");

            List<ElementHandle> list = page.querySelector("div[data-spm=\"main\"]").querySelectorAll("a.card--container--2h3RcUB.cards--gallery--2o6yJVt");

            for (ElementHandle element:
                    list) {
                ElementHandle titleHandle = element.querySelector("div.card--title--XbBBi0C.cards--title--2rMisuY");
                String title = titleHandle.innerText();

                ElementHandle priceHandle = element.querySelector("div.card--price--1vog9ZD.cards--price--1Ieyi-z");
                SplitedPrice splitedPrice = new SplitedPrice(priceHandle.innerText());
                double price = splitedPrice.getPrice();
                String priceCurrency = splitedPrice.getCurrency();

                ElementHandle cardsHandle =  element.querySelector("span.cards--store--A2ezoRc");
                String cards = cardsHandle.innerText();

                String servicesContainer = new String("");
                try {
                    ElementHandle servicesContainerHandle =  element.querySelector("div.card--servicesContainer--2RFr8_L.cards--service--2jqKJWn");
                    if(servicesContainerHandle != null)
                        servicesContainer = servicesContainerHandle.innerText();
                }
                catch(NullPointerException e){
                    System.err.println(e.getMessage());
                }

                int sold = 0;
                String soldLabel = "sprzedano";
                try {
                    ElementHandle tradeInfoHandle = element.querySelector("span.card--trade--3uRoUYx");
                    if(tradeInfoHandle != null){
                        SplitedSold splitedSold = new SplitedSold(tradeInfoHandle.innerText());
                        sold = splitedSold.getSold();
                        soldLabel = splitedSold.getSoldLabel();
                    }
                }
                catch(NullPointerException e){
                    System.err.println(e.getMessage());
                }

                String evaluation = new String();
                try {
                    ElementHandle evaluationHandle =  element.querySelector("span.card--evaluation");
                    if(evaluationHandle != null)
                        evaluation = evaluationHandle.innerText().replace(",",".");
                }
                catch(NullPointerException e){
                    evaluation = "";
                    System.err.println(e.getMessage());
                }

                CardContainer cardContainer = new CardContainer(title, price, priceCurrency, cards, servicesContainer, sold, evaluation);
                addRecord(cardContainer);
            }
            browser.close();
        }
    }

    public List<CardContainer> getMainDataSPM() {
        System.out.println("[loadData] data...");
        clear();
        DynamicScraping();
        return records;
    }

    public void addRecord(CardContainer record) {
        if(!records.contains(record))
            records.add(record);
    }

    public List<CardContainer> getMainDataSPM(String title) {
        System.out.println("[selectedData] selected data by title...");
        records = records.stream()
                .filter(x -> x.getTitle().contains(title))
                .collect(Collectors.toList());
        return records;
    }

    private static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }

    public Object setSearchtitle(String title) {
        this.searchTitle = title;
        return this.searchTitle;
    }

    public String getSearchtitle() {
        return this.searchTitle;
    }

    private class SplitedPrice{
        private double price = Double.NaN;
        private String currency = "zł";

        public SplitedPrice(String priceInText) {

            String pattern = "(\\d+,\\d+)(\\D+)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(priceInText);
            if (m.find( )) {
                this.price = Double.valueOf(m.group(1).replace(",","."));
                this.currency = m.group(2);
            }else {
                System.out.println("Price NO MATCH");
            }
        }

        public double getPrice() {
            return this.price;
        }

        public String getCurrency() {
            return this.currency;
        }
    }

    private class SplitedSold{
        private int sold = 0;
        private String soldLabel = "sprzedano";

        public SplitedSold(String soldInText) {

            String pattern = "(\\d+)\\s+(\\w+)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(soldInText);
            if (m.find( )) {
                this.sold = Integer.parseInt(m.group(1));
                this.soldLabel = m.group(2);
            }else {
                System.out.println("Price NO MATCH");
            }
        }

        public int getSold() {
            return this.sold;
        }

        public String getSoldLabel() {
            return this.soldLabel;
        }
    }

    public static List<CardContainer> getRecords() {
        return records;
    }

    public void clear() {
        records.clear();
    }
}
