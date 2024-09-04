package com.kwilde.gainloss.service;

import com.kwilde.gainloss.dto.PortfolioGainLoss;
import com.kwilde.gainloss.entity.PortfolioRecord;

import java.util.List;

public interface PortfolioService {

    boolean importData();

    boolean importFromFile(String fileName);

    List<PortfolioRecord> findAll();

    List<Object[]> getLatestVsPreviousTickers();
    List<Object[]> getLatestVsOldestTickers();

    List<PortfolioGainLoss> getLatestVsPreviousPortfolios();
    List<PortfolioGainLoss> getLatestVsOldestPortfolios();


}
