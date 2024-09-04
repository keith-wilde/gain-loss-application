package com.kwilde.gainloss.dto;

import java.math.BigDecimal;

public class PortfolioGainLoss {

    private String name;

    private BigDecimal overallGainloss;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOverallGainloss() {
        return overallGainloss;
    }

    public void setOverallGainloss(BigDecimal overallGainloss) {
        this.overallGainloss = overallGainloss;
    }
}
