package com.kwilde.gainloss.service;

import com.kwilde.gainloss.dto.PortfolioGainLoss;
import com.kwilde.gainloss.dto.TickerGainLoss;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {

    boolean importData();

    boolean importFromFile(String fileName);

    List<TickerGainLoss> getLatestVsPreviousTickers();

    List<TickerGainLoss> getLatestVsOldestTickers();

    List<TickerGainLoss> rankTickersWithinPortfolio(String name);

    List<PortfolioGainLoss> getLatestVsPreviousPortfolios();

    List<PortfolioGainLoss> getLatestVsOldestPortfolios();

    Optional<PortfolioGainLoss> findBestPerformingPortfolio();

}
