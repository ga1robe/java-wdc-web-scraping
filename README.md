## java-wdc-web-scraping

### krótki opis

System "java-wdc-web-scraping" służy do przedstawienia ofert i produktów na sprzedaż w sklepie alliexpress.pl.

### Wykorzystane technologie

Java, SpringBoot, Spring MVC, SpringWeb, JSP,
MySQL, H2,
playwright, poi-ooxml, JSoup

### Dokładniejszy opis

Zbudowanie systemu w technologii SpringBoot do prezentacji
ofert i produktów na sprzedaż. Wykorzystanie odpowiednich struktur dla przechowywania danych
zbudowanych w pamięci z wykorzystaniem bazy danych.

System posiada jedną formatkę w postaci dwóch list. Każdą z list można zapisać oddzielnie do pliku typu excel.

Dane są wprowadzene i zapisywane do struktur danych w tle w wątku, z możliwością aktualizacją co 15 minut.

Po załadowaniu danych do bazy, jest możliwość załadowaniu strony front-end z pełnymi danymi.

### Opis formatki

Poniżej przedstawiono cztery zrzuty ekranu, powiązane ze stroną JSP.
Na obrazach są ekrany przestawiające ofert i produktów na sprzedaż.
Na obrazkach pod nimi są ekrany przedstawiający okienku zapisu danych do pliku,
po naciśnięciu klawisza "zapisz".

![Ekran prezentacji ofert do sprzedaży](https://github.com/ga1robe/java-wdc-web-scraping/blob/master/screenshots/dropshipping_oferty.png)

![Ekran prezentacji ofert do sprzedaży z zapisem do pliku](https://github.com/ga1robe/java-wdc-web-scraping/blob/master/screenshots/dropshipping_oferts_zapis_do_pliku.png)

![Ekran prezentacji produktów do sprzedaży](https://github.com/ga1robe/java-wdc-web-scraping/blob/master/screenshots/dropshipping_products_to_sell.png)

![Ekran prezentacji produktów do sprzedaży z zapisem do pliku](https://github.com/ga1robe/java-wdc-web-scraping/blob/master/screenshots/dropshipping_products_to_sell_zapis_do_pliku.png)

