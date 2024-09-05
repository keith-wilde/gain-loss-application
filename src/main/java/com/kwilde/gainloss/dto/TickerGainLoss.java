package com.kwilde.gainloss.dto;

import java.math.BigDecimal;

public class TickerGainLoss {

    private String portfolioName;
    private String ticker;
    private BigDecimal gainLoss;

    public TickerGainLoss() {}

    public TickerGainLoss(String portfolioName, String ticker, BigDecimal gainLoss) {
        this.portfolioName = portfolioName;
        this.ticker = ticker;
        this.gainLoss = gainLoss;
    }

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

    public BigDecimal getGainLoss() {
        return gainLoss;
    }

    public void setGainLoss(BigDecimal gainLoss) {
        this.gainLoss = gainLoss;
    }
}
