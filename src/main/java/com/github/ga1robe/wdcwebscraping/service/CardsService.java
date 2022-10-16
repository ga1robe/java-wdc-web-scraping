package com.github.ga1robe.wdcwebscraping.service;

import javax.annotation.PostConstruct;

import com.github.ga1robe.wdcwebscraping.model.CardContainer;

import com.github.ga1robe.wdcwebscraping.model.WDCenterLocators;
import com.github.ga1robe.wdcwebscraping.model.WDCenterOccasionsLocators;
import com.github.ga1robe.wdcwebscraping.model.WDCenterSearchTextLocators;
import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.spi.HttpServerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.ga1robe.wdcwebscraping.util.ExcelGenerator;

import org.springframework.stereotype.Service;

@Service
public class CardsService {

    private static List<CardContainer> occasionsRecords = new ArrayList<CardContainer>();
    private static List<CardContainer> searchTextRecords = new ArrayList<CardContainer>();
    private static String searchTitle = new String();

    private static WDCenterLocators wdCenterSearchTextLocators = new WDCenterSearchTextLocators();
    private static WDCenterLocators wdCenterOccasionsLocators = new WDCenterOccasionsLocators();

    @PostConstruct
    public void loadData() {
        clear();
        System.out.println("[loadData] Adding data on startup...");
        do {
            DynamicScraping(occasionsRecords, wdCenterOccasionsLocators);
            if(occasionsRecords.size() == 0) {
                try {
                    Thread.sleep(3 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while(occasionsRecords.size() == 0);
        do {
            DynamicScraping(searchTextRecords, wdCenterSearchTextLocators);
            if(searchTextRecords.size() == 0) {
                try {
                    Thread.sleep(3 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while(searchTextRecords.size() == 0);
    }

    private static void DynamicScraping(List<CardContainer> records, WDCenterLocators wdCenterLocators) {
        try (Playwright playwright = Playwright.create()) {
            final BrowserType chromium = playwright.chromium();
            final Browser browser = chromium.launch();
            final Page page = browser.newPage();
            page.navigate(wdCenterLocators.getUrl());
            String listGalleryLinksSelector = wdCenterLocators.getListGalleryLinks();
            List<ElementHandle> list = page.querySelectorAll(listGalleryLinksSelector);
            System.out.printf("[check-point] list.size(): %d for URL: %s with listGalleryLinksSelector: \'%s\'\n", list.size(), wdCenterLocators.getUrl(), listGalleryLinksSelector);
            for (ElementHandle element:
                    list) {
                String cardContainerAHref = element.getProperty("href").toString();
                String cardContainerATarget = element.getProperty("target").toString();

                String titleSelector = wdCenterLocators.getTitle();
                ElementHandle titleHandle = element.querySelector(titleSelector);
                String titleText = "";
                if(titleHandle != null)
                    titleText = titleHandle.innerText();
                else
                    titleText = "NO TITLE";
                String title = "<a href=\""+cardContainerAHref+"\" target=\""+cardContainerATarget+"\">".concat(titleText).concat("</a>");

                String priceSelector = wdCenterLocators.getPrice();
                ElementHandle priceHandle = element.querySelector(priceSelector);
                SplitedPrice splitedPrice = new SplitedPrice(priceHandle.innerText());
                double price = splitedPrice.getPrice();
                String priceCurrency = splitedPrice.getCurrency();

                String cardsStoreLinkSelector = wdCenterLocators.getCardsStoreLink();
                ElementHandle cardsStoreLinkHandle =  element.querySelector(cardsStoreLinkSelector);
                String cardsStoreAHref = cardsStoreLinkHandle.getAttribute("href");
                String cardsStoreARole = cardsStoreLinkHandle.getAttribute("role");
                String cardsStoreATarget = cardsStoreLinkHandle.getAttribute("target");
                String cardsStoreLinkText = "";
                if(cardsStoreLinkHandle != null)
                    cardsStoreLinkText = cardsStoreLinkHandle.innerText();
                else
                    cardsStoreLinkText = "NO CARDS STORE";
                String cardsStore = "<a role=\""+cardsStoreARole+"\" href=\""+cardsStoreAHref+"\" target=\""+cardsStoreATarget+"\">".concat(cardsStoreLinkText).concat("</a>");

                String tradeInfoSelector = wdCenterLocators.getTradeInfo();
                int soldNumber = 0;
                String soldLabel = "sprzedano";
                if(tradeInfoSelector != null && element.querySelector(tradeInfoSelector) != null) {
                    ElementHandle tradeInfoHandle = element.querySelector(tradeInfoSelector);
                    SplitedSold splitedSold = new SplitedSold(tradeInfoHandle.innerText());
                    soldNumber = splitedSold.getSold();
                    soldLabel = splitedSold.getSoldLabel();
                }

                String evaluationSelector = wdCenterLocators.getEvaluation();
                String evaluation = new String("");
                if(evaluationSelector != null && element.querySelector(evaluationSelector) != null) {
                    ElementHandle evaluationHandle = element.querySelector(evaluationSelector);
                    evaluation = evaluationHandle.innerText();
                }

                String servicesContainerSelector = wdCenterLocators.getServicesContainer();
                String servicesContainer = new String("");
                if(servicesContainerSelector != null && element.querySelector(servicesContainerSelector) != null) {
                    ElementHandle servicesContainerHandle =  element.querySelector(servicesContainerSelector);
                    servicesContainer = servicesContainerHandle.innerText();
                }

                String savesContainerSelector = wdCenterLocators.getSavesContainer();
                String savesContainer = new String("");
                if(savesContainerSelector != null && element.querySelector(savesContainerSelector) != null) {
                    ElementHandle savesContainerHandle =  element.querySelector(savesContainerSelector);
                    savesContainer = savesContainerHandle.innerText();
                }

                String salesContainerSelector = wdCenterLocators.getSalesContainer();
                String salesContainer = new String("");
                if(salesContainerSelector != null && element.querySelector(salesContainerSelector) != null) {
                    ElementHandle salesContainerHandle =  element.querySelector(salesContainerSelector);
                    salesContainer = salesContainerHandle.innerText();
                }

                String placeHolderSelector = wdCenterLocators.getPlaceHolder();
                String placeHolder = new String("");
                if(placeHolderSelector != null && element.querySelector(placeHolderSelector) != null) {
                    ElementHandle placeHolderHandle =  element.querySelector(placeHolderSelector);
                    placeHolder = placeHolderHandle.innerText();
                }
                addRecord(records, new CardContainer(title, price, priceCurrency, cardsStore, servicesContainer, soldNumber, evaluation, savesContainer, salesContainer, placeHolder));
            }
            browser.close();
        }
    }


    public List<CardContainer> getMainDataSPM() {
        clear(occasionsRecords);
        System.out.println("[loadData] data...");
        do {
            DynamicScraping(occasionsRecords, wdCenterOccasionsLocators);
        } while(occasionsRecords.size() == 0);
        return occasionsRecords;
    }

    public List<CardContainer> getProductToSell() {
        clear(searchTextRecords);
        System.out.println("[loadData] data...");
        do {
            DynamicScraping(searchTextRecords, wdCenterSearchTextLocators);
        } while(searchTextRecords.size() == 0);
        return searchTextRecords;
    }

    public static void addRecord(List<CardContainer> records, CardContainer record) {
        if(!records.contains(record))
            records.add(record);
    }

    public List<CardContainer> getMainDataSPM(String title) {
        System.out.println("[selectedData] selected data by title...");
        occasionsRecords = occasionsRecords.stream()
                .filter(x -> x.getTitle().contains(title))
                .collect(Collectors.toList());
        return occasionsRecords;
    }

    public List<CardContainer> getProductToSell(String title) {
        System.out.println("[selectedData] selected data by title...");
        searchTextRecords = searchTextRecords.stream()
                .filter(x -> x.getTitle().contains(title))
                .collect(Collectors.toList());
        return searchTextRecords;
    }

    public Object setSearchtitle(String title) {
        this.searchTitle = title;
        return this.searchTitle;
    }

    public String getSearchtitle() {
        return this.searchTitle;
    }

    public String writeMainDataSPM(String title, HttpServletResponse response) throws IOException {
        System.out.println("[check-point] TODO. place to write \'occasionsRecords\' data to file");
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+title+"_records_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelGenerator generator = new ExcelGenerator(occasionsRecords);
        try {
            generator.generate(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "saved";
    }

    public String writeProductToSell(String title, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+title+"_records_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelGenerator generator = new ExcelGenerator(searchTextRecords);
        try {
            generator.generate(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "saved";
    }

    private static class SplitedPrice{
        private double price = Double.NaN;
        private String currency = "zł";

        public SplitedPrice(String priceInText) {

            String pattern;
            if(priceInText.contains(",")) {
                pattern = "(\\d+,\\d+)(\\D+)";
            } else {
                pattern = "(\\d+)(\\D+)";
            }
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(priceInText);
            if (m.find()) {
                this.price = Double.valueOf(m.group(1).replace(",", "."));
                this.currency = m.group(2);
            } else {
                System.out.printf("Price NO MATCH in \'%s\'\n", priceInText);
            }
        }

        public double getPrice() {
            return this.price;
        }

        public String getCurrency() {
            return this.currency;
        }
    }

    private static class SplitedSold{
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
                System.out.printf("Sold NO MATCH in \'%s\'", soldInText);
            }
        }

        public int getSold() {
            return this.sold;
        }

        public String getSoldLabel() {
            return this.soldLabel;
        }
    }

    public static List<CardContainer> getOccasionsRecords() {
        return occasionsRecords;
    }

    public static List<CardContainer> getSearchTextRecords() {
        return searchTextRecords;
    }

    public static void clear() {
        if(searchTextRecords.size() > 0)
            searchTextRecords.clear();
        if(occasionsRecords.size() > 0)
            occasionsRecords.clear();
    }

    private void clear(List<CardContainer> records) {
        records.clear();
    }
}