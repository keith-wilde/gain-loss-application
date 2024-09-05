package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.entity.Portfolio;
import com.kwilde.gainloss.file.PortfolioFileHeaders;
import com.kwilde.gainloss.service.FileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.kwilde.gainloss.file.PortfolioFileHeaders.*;

@Service
public class CSVFileService implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(CSVFileService.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withHeader(PortfolioFileHeaders.class).withSkipHeaderRecord();


    @Value("${portfolio.default-file}")
    private String defaultFilePath;

    @Override
    public List<Portfolio> parseAll() {

        List<Portfolio> portfolioList = new ArrayList<>();

        Reader in;
        try {
            in = new FileReader(ResourceUtils.getFile(defaultFilePath));
            Iterable<CSVRecord> records = CSV_FORMAT.parse(in);

            //NOTE: assuming csv values are valid and populated
            for (CSVRecord record : records) {
                Portfolio portfolio = populatePortfolioRecord(record);
                portfolioList.add(portfolio);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Processed {} records", portfolioList.size());

        return portfolioList;
    }

    @Override
    public List<Portfolio> parseByFileName(String name) {
        List<Portfolio> portfolioList = new ArrayList<>();

        Reader in;
        try {
            in = new FileReader(ResourceUtils.getFile(name));
            Iterable<CSVRecord> records = CSV_FORMAT.parse(in);

            //NOTE: assuming csv values are valid and populated
            for (CSVRecord record : records) {
                Portfolio portfolio = populatePortfolioRecord(record);
                portfolioList.add(portfolio);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Processed {} records", portfolioList.size());

        return portfolioList;
    }

    private static Portfolio populatePortfolioRecord(CSVRecord record) {
        Portfolio portfolio = new Portfolio();

        portfolio.setPortfolioName(record.get(PORTFOLIO));
        portfolio.setTicker(record.get(TICKER));
        portfolio.setDate(LocalDate.parse(record.get(DATE), FORMATTER));
        portfolio.setOpenPrice(new BigDecimal(record.get(OPEN)));
        portfolio.setHighPrice(new BigDecimal(record.get(HIGH)));
        portfolio.setLowPrice(new BigDecimal(record.get(LOW)));
        portfolio.setClosePrice(new BigDecimal(record.get(CLOSE)));
        portfolio.setAdjustedClosePrice(new BigDecimal(record.get(ADJUSTED_CLOSE)));
        portfolio.setVolume(Long.valueOf(record.get(VOLUME)));
        portfolio.setQuantity(new BigDecimal(record.get(QUANTITY)));
        portfolio.setMarketValue(new BigDecimal(record.get(MARKET_VALUE)));
        return portfolio;
    }

}
