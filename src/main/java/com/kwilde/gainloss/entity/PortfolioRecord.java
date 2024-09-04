package com.kwilde.gainloss.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;


@Entity
@Table(name = "portfolio")
public class PortfolioRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String portfolioName;

    private String ticker;

    private LocalDate date;

    private BigDecimal openPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private BigDecimal closePrice;

    private BigDecimal adjustedClosePrice;

    private Long volume;

    private BigDecimal quantity;

    private BigDecimal marketValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getAdjustedClosePrice() {
        return adjustedClosePrice;
    }

    public void setAdjustedClosePrice(BigDecimal adjustedClosePrice) {
        this.adjustedClosePrice = adjustedClosePrice;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", PortfolioRecord.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("portfolioName='" + portfolioName + "'")
                .add("ticker='" + ticker + "'")
                .add("date=" + date)
                .add("openPrice=" + openPrice)
                .add("highPrice=" + highPrice)
                .add("lowPrice=" + lowPrice)
                .add("closePrice=" + closePrice)
                .add("adjustedClosePrice=" + adjustedClosePrice)
                .add("volume=" + volume)
                .add("quantity=" + quantity)
                .add("marketValue=" + marketValue)
                .toString();
    }
}
