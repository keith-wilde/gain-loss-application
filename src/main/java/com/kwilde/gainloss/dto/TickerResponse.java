package com.kwilde.gainloss.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TickerResponse {

    private String name;
    private LocalDate date;
    private BigDecimal gainLoss;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getGainLoss() {
        return gainLoss;
    }

    public void setGainLoss(BigDecimal gainLoss) {
        this.gainLoss = gainLoss;
    }
}
