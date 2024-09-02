package com.kwilde.gainloss.file;

public enum PortfolioFileHeaders {

    PORTFOLIO("Portfolio"),
    TICKER("Ticker"),
    DATE("Date"),
    OPEN("Open"),
    HIGH("High"),
    LOW("Low"),
    CLOSE("Close"),
    ADJUSTED_CLOSE("Adj Close"),
    VOLUME("Volume"),
    QUANTITY("Quantity"),
    MARKET_VALUE("Market Value");

    private String title;
    PortfolioFileHeaders(String header) {
        this.title = header;
    }

    public String getTitle() {
        return title;
    }
}
