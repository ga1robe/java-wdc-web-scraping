package com.github.ga1robe.wdcwebscraping.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name="CARDRECORDS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CardContainer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=2, max=25, message="Title must be 2-25 characters long")
    private String title;

    @NotNull
    private double price;

    @NotNull
    private String priceCurrency;

    @NotNull
    private String cards;

    @Null
    private String servicesContainer;

    @Null
    private int sold;

    @Null
    private double evaluation;

    public CardContainer(String title, double price, String priceCurrency, String cards, String servicesContainer, int sold, double evaluation) {
        this.title = title;
        this.price = price;
        this.priceCurrency = priceCurrency;
        this.cards = cards;
        this.servicesContainer = servicesContainer;
        this.sold = sold;
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "CardContainer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                "'" + priceCurrency + '\'' +
                ", cards='" + cards + '\'' +
                ", servicesContainer='" + servicesContainer + '\'' +
                ", sold=" + sold +
                ", evaluation=" + evaluation +
                '}';
    }
}