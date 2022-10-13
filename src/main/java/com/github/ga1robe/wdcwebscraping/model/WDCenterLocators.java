package com.github.ga1robe.wdcwebscraping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class WDCenterLocators {

    @NotEmpty(message = "url field is missing")
    @NonNull
    private String url;
    @NotEmpty(message = "locator of listGalleryLinks is missing")
    @NonNull
    private String listGalleryLinks;
    @NonNull
    private String title;
    @NonNull
    private String price;
    @NonNull
    private String cardsStoreLink;
    @Null
    private String tradeInfo;
    @Null
    private String evaluation;
    @Null
    private String servicesContainer;
    @Null
    private String placeHolder;
    @Null
    private String savesContainer;
    @Null
    private String salesContainer;
}
