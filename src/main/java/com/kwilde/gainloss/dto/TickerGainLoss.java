package com.kwilde.gainloss.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TickerGainLoss {

    private String portfolioName;
    private String ticker;
    private LocalDate date;
    private BigDecimal gainLoss;

    public String getName() {
        return ticker;
    }

    public void setName(String name) {
        this.ticker = name;
    }


    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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
