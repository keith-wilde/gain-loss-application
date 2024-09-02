package com.kwilde.gainloss.dto;

import java.util.List;

public class PortfolioResponse {

    private String name;

    private List<TickerResponse> tickerList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TickerResponse> getTickerList() {
        return tickerList;
    }

    public void setTickerList(List<TickerResponse> tickerList) {
        this.tickerList = tickerList;
    }
}
