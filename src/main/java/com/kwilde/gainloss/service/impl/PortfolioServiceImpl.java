package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.dto.PortfolioGainLoss;
import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.repository.PortfolioRepository;
import com.kwilde.gainloss.service.FileService;
import com.kwilde.gainloss.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.*;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);

    private final FileService fileService;
    private final PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(FileService fileService, PortfolioRepository portfolioRepository) {
        this.fileService = fileService;
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public boolean importData() {
        List<PortfolioRecord> portfolioRecordList = fileService.parseAll();
        if(CollectionUtils.isEmpty(portfolioRecordList)){
            logger.info("No portfolio records found");
            return false;
        }

        portfolioRepository.saveAll(portfolioRecordList);

        return true;
    }

    @Override
    public boolean importFromFile(String fileName) {
        List<PortfolioRecord> portfolioRecordList = fileService.parseByFileName(fileName);
        if(CollectionUtils.isEmpty(portfolioRecordList)){
            logger.info("No portfolio records found for file {}", fileName);
            return false;
        }

        portfolioRepository.saveAll(portfolioRecordList);

        return true;
    }

    @Override
    public List<PortfolioRecord> findAll() {
        return portfolioRepository.findAll();
    }

    @Override
    public List<Object[]> getLatestVsPreviousTickers() {
        LocalDate latestTickerDate = portfolioRepository.findLatestDate();
        Objects.requireNonNull(latestTickerDate);
        LocalDate previousTickerDate = latestTickerDate.minusWeeks(1);

        return portfolioRepository.findTickerGainLossByDate(previousTickerDate, latestTickerDate);
    }

    @Override
    public List<Object[]> getLatestVsOldestTickers() {
        return portfolioRepository.findTickerGainLossByDate(
                Objects.requireNonNull(portfolioRepository.findEarliestDate()),
                Objects.requireNonNull(portfolioRepository.findLatestDate())
        );
    }

    @Override
    public List<PortfolioGainLoss> getLatestVsPreviousPortfolios() {

        List<String> portfolioNames = portfolioRepository.findAllPortfolioNames();

        List<PortfolioGainLoss> portfolioGainLosses = new ArrayList<>();

        if(ObjectUtils.isEmpty(portfolioNames)){
            logger.info("No Portfolio names found");
            return new ArrayList<>();
        }

        LocalDate latestDate = Objects.requireNonNull(portfolioRepository.findLatestDate());

        for(String name: portfolioNames){
            List<Object[]> tickerObjectList = portfolioRepository.findPortfolioGainLossByDate(
                    name,
                    latestDate.minusWeeks(1),
                    latestDate
            );

            if(!CollectionUtils.isEmpty(tickerObjectList)){
                PortfolioGainLoss portfolioGainLoss = new PortfolioGainLoss();
                portfolioGainLoss.setName(name);
                // sum up ticker gain/losses
                portfolioGainLosses.add(portfolioGainLoss);
            }
        }

        return portfolioGainLosses;
    }

    //TODO, put common logic into private method
    @Override
    public List<PortfolioGainLoss> getLatestVsOldestPortfolios() {

        List<String> portfolioNames = portfolioRepository.findAllPortfolioNames();

        List<PortfolioGainLoss> portfolioGainLosses = new ArrayList<>();

        if(ObjectUtils.isEmpty(portfolioNames)){
            logger.info("No Portfolio names found");
            return new ArrayList<>();
        }

        for(String name: portfolioNames){
            List<Object[]> tickerObjectList = portfolioRepository.findPortfolioGainLossByDate(
                    name,
                    Objects.requireNonNull(portfolioRepository.findEarliestDate()),
                    Objects.requireNonNull(portfolioRepository.findLatestDate())
            );

            if(!CollectionUtils.isEmpty(tickerObjectList)){
                PortfolioGainLoss portfolioGainLoss = new PortfolioGainLoss();
                portfolioGainLoss.setName(name);
                // todo sum up ticker gain/losses

                portfolioGainLosses.add(portfolioGainLoss);
            }

        }


        return portfolioGainLosses;
    }
}
