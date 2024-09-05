package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.dto.PortfolioGainLoss;
import com.kwilde.gainloss.dto.TickerGainLoss;
import com.kwilde.gainloss.entity.Portfolio;
import com.kwilde.gainloss.repository.PortfolioRepository;
import com.kwilde.gainloss.service.FileService;
import com.kwilde.gainloss.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.kwilde.gainloss.util.ObjectUtils.convertToBigDecimal;

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
        List<Portfolio> portfolioList = fileService.parseAll();
        if (CollectionUtils.isEmpty(portfolioList)) {
            logger.info("No portfolio records found");
            return false;
        }

        portfolioRepository.saveAll(portfolioList);

        return true;
    }

    @Override
    public boolean importFromFile(String fileName) {
        List<Portfolio> portfolioList = fileService.parseByFileName(fileName);
        if (CollectionUtils.isEmpty(portfolioList)) {
            logger.info("No portfolio records found for file {}", fileName);
            return false;
        }

        portfolioRepository.saveAll(portfolioList);

        return true;
    }

    @Override
    public List<TickerGainLoss> getLatestVsPreviousTickers() {
        LocalDate latestTickerDate = portfolioRepository.findLatestDate();
        Objects.requireNonNull(latestTickerDate, "Latest ticker date not found");
        LocalDate previousTickerDate = latestTickerDate.minusWeeks(1);

        List<Object[]> tickerObjectList = portfolioRepository.findTickerGainLossByDate(previousTickerDate, latestTickerDate);

        return convertObjectsToTickers(tickerObjectList);
    }

    @Override
    public List<TickerGainLoss> getLatestVsOldestTickers() {
        List<Object[]> tickerObjectList = portfolioRepository.findTickerGainLossByDate(
                Objects.requireNonNull(portfolioRepository.findEarliestDate()),
                Objects.requireNonNull(portfolioRepository.findLatestDate())
        );

        return convertObjectsToTickers(tickerObjectList);
    }

    @Override
    public List<TickerGainLoss> rankTickersWithinPortfolio(String name) {
        Objects.requireNonNull(name, "Portfolio name cannot be empty");

        List<Object[]> tickerObjectList = portfolioRepository.findPortfolioGainLossByDate(
                name,
                Objects.requireNonNull(portfolioRepository.findEarliestDate()),
                Objects.requireNonNull(portfolioRepository.findLatestDate())
        );

        List<TickerGainLoss> tickerGainLosses = convertObjectsToTickers(tickerObjectList);

        return rankTickers(tickerGainLosses);
    }

    @Override
    public List<PortfolioGainLoss> getLatestVsPreviousPortfolios() {

        List<String> portfolioNames = portfolioRepository.findAllPortfolioNames();

        List<PortfolioGainLoss> portfolioGainLosses = new ArrayList<>();

        if (ObjectUtils.isEmpty(portfolioNames)) {
            logger.info("No Portfolio names found");
            return new ArrayList<>();
        }

        LocalDate latestDate = Objects.requireNonNull(portfolioRepository.findLatestDate());

        for (String name : portfolioNames) {
            List<Object[]> tickerObjectList = portfolioRepository.findPortfolioGainLossByDate(
                    name,
                    latestDate.minusWeeks(1),
                    latestDate
            );

            if (!CollectionUtils.isEmpty(tickerObjectList)) {
                PortfolioGainLoss portfolioGainLoss = new PortfolioGainLoss();
                portfolioGainLoss.setName(name);
                portfolioGainLoss.setOverallGainloss(sumTickers(tickerObjectList));
                portfolioGainLosses.add(portfolioGainLoss);
            }
        }

        return portfolioGainLosses;
    }

    @Override
    public List<PortfolioGainLoss> getLatestVsOldestPortfolios() {

        List<String> portfolioNames = portfolioRepository.findAllPortfolioNames();

        List<PortfolioGainLoss> portfolioGainLosses = new ArrayList<>();

        if (ObjectUtils.isEmpty(portfolioNames)) {
            logger.info("No Portfolio names found");
            return new ArrayList<>();
        }

        for (String name : portfolioNames) {
            List<Object[]> tickerObjectList = portfolioRepository.findPortfolioGainLossByDate(
                    name,
                    Objects.requireNonNull(portfolioRepository.findEarliestDate()),
                    Objects.requireNonNull(portfolioRepository.findLatestDate())
            );

            if (!CollectionUtils.isEmpty(tickerObjectList)) {
                PortfolioGainLoss portfolioGainLoss = new PortfolioGainLoss();
                portfolioGainLoss.setName(name);
                portfolioGainLoss.setOverallGainloss(sumTickers(tickerObjectList));
                portfolioGainLosses.add(portfolioGainLoss);
            }
        }
        return portfolioGainLosses;
    }

    @Override
    public Optional<PortfolioGainLoss> findBestPerformingPortfolio() {

        List<PortfolioGainLoss> portfolioGainLosses = getLatestVsOldestPortfolios();

        if (CollectionUtils.isEmpty(portfolioGainLosses)) {
            return Optional.empty();
        }

        return portfolioGainLosses.stream()
                .max(Comparator.comparing(PortfolioGainLoss::getOverallGainloss));
    }

    private BigDecimal sumTickers(List<Object[]> tickers) {
        BigDecimal total = BigDecimal.ZERO;
        for (Object[] ticker : tickers) {
            total = total.add(convertToBigDecimal(ticker[2]));
        }

        return total;
    }

    private List<TickerGainLoss> convertObjectsToTickers(List<Object[]> tickerObjectList) {
        List<TickerGainLoss> tickerGainLosses = new ArrayList<>();
        if (CollectionUtils.isEmpty(tickerObjectList)) {
            logger.info("No tickers found");
        }

        for (Object[] ticker : tickerObjectList) {
            tickerGainLosses.add(
                    new TickerGainLoss(
                            String.valueOf(ticker[0]),
                            String.valueOf(ticker[1]),
                            convertToBigDecimal(ticker[2])
                    )
            );

        }

        return tickerGainLosses;

    }

    private List<TickerGainLoss> rankTickers(List<TickerGainLoss> tickerGainLosses) {
        return tickerGainLosses.stream()
                .sorted((tickerGainLoss1, tickerGainLoss2)
                        -> tickerGainLoss2.getGainLoss().compareTo(tickerGainLoss1.getGainLoss()))
                .toList();
    }

}
