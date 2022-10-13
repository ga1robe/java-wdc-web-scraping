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
public class WDCenterSearchTextLocators extends WDCenterLocators {
    @NotEmpty(message = "url field is missing")
    @NonNull
    private String url = "https://pl.aliexpress.com/wholesale?catId=0&SearchText=dropshipping+center+2022+products+to+sell&spm=a2g0o.productlist.1000002.0";
    @NonNull
    private String listGalleryLinks = "div#root div.glosearch-wrap div.page-content div.main-content div.right-menu div.product-container div.JIIxO a._3t7zg._2f4Ho";
    @NonNull
    private String title = "div._3GR-w div._1tu1Z.Vgu6S h1";
    @NonNull
    private String price = "div._3GR-w div.mGXnE._37W_B";
    @NonNull
    private String cardsStoreLink = "div._3GR-w span._7CHGi a.ox0KZ";
    @Null
    private String tradeInfo = "div._3GR-w div.ZzMrp span._1kNf9";
    @Null
    private String evaluation = "div._3GR-w div.ZzMrp span.eXPaM";
    @Null
    private String servicesContainer = "div.KnIS-._4juNd span._2jcMA";
    @Null
    private String placeHolder = "div._3GR-w div._21aoB";
    @Null
    private String savesContainer = "div.g_XRl";
    @Null
    private String salesContainer = "div._11_8K";
}
