package com.github.ga1robe.wdcwebscraping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@Getter
@Setter
public class WDCenterOccasionsLocators extends WDCenterLocators {
    @NotEmpty(message = "url field is missing")
    @NonNull
    private String url = "https://pl.aliexpress.com/w/wholesale-dropshipping-center.html";
    @NonNull
    private String listGalleryLinks = "html body div#root div.root--container--2gVZ5S0 div.main--mainContainer--3C9jgV- div.right--container--1WU9aL4.right--hasPadding--52H__oG div.right--content--HqEDmtj div.content--container--2dDeH1y div.list--gallery--34TropR a.card--container--2h3RcUB.cards--gallery--2o6yJVt";
    @NonNull
    private String title = "div.card--content--B1hOBiI div.card--title--XbBBi0C.cards--title--2rMisuY h1.card--titleText--1Mgo0PW";
    @NonNull
    private String price = "div.card--content--B1hOBiI div.card--price--1vog9ZD.cards--price--1Ieyi-z";
    @NonNull
    private String cardsStoreLink = "div.card--content--B1hOBiI span.cards--store--A2ezoRc a.cards--storeLink--1_xx4cD";
    @Null
    private String tradeInfo = "div.card--content--B1hOBiI div.card--tradeContainer--2XjLeML";
    @Null
    private String evaluation = "div.card--content--B1hOBiI div.card--tradeContainer--2XjLeML span.card--evaluation--2gaqVNL";
    @Null
    private String servicesContainer = "div.card--content--B1hOBiI div.card--servicesContainer--2RFr8_L.cards--service--2jqKJWn div span.card--servicesText--136AX9u";
    @Null
    private String placeHolder = "div.card--content--B1hOBiI div.cards--placeHolder--2N7hxIf";
    @Null
    private String savesContainer = "div.card--content--B1hOBiI div.card--couponsInfo--1-4x3Dk span";
    @Null
    private String salesContainer;
}
