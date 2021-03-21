package com.myretail.pricechecker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Queue;

/**
 * @author Kevin Ekue created on 3/21/2021
 */


public class Price {
//    private String id;
    private double value;
    private String currency; //TODO: Consider using Big Decimals and Enums
//    private Queue<Map<LocalDateTime,Double>> priceHistory;

    /**
   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    */


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
    public Queue<Map<LocalDateTime, Double>> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(Queue<Map<LocalDateTime, Double>> priceHistory) {
        this.priceHistory = priceHistory;
    }
     */
}
