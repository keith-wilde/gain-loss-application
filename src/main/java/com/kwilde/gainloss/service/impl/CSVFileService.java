package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.file.PortfolioFileHeaders;
import com.kwilde.gainloss.service.FileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
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

//   TODO  @value for file location?

    @Override
    public List findAll() {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(PortfolioFileHeaders.class).withSkipHeaderRecord();
        List<PortfolioRecord> portfolioRecordList = new ArrayList<>();

        Reader in;
        try {
            in = new FileReader(ResourceUtils.getFile("classpath:data.csv"));
            Iterable<CSVRecord> records = csvFormat.parse(in);

            //NOTE: assuming csv values are valid and populated
            for (CSVRecord record : records) {
                PortfolioRecord portfolioRecord = new PortfolioRecord();

                portfolioRecord.setPortfolioName(record.get(PORTFOLIO));
                portfolioRecord.setTicker(record.get(TICKER));
                portfolioRecord.setDate(LocalDate.parse(record.get(DATE), FORMATTER));
                portfolioRecord.setOpenPrice(new BigDecimal(record.get(OPEN)));
                portfolioRecord.setHighPrice(new BigDecimal(record.get(HIGH)));
                portfolioRecord.setLowPrice(new BigDecimal(record.get(LOW)));
                portfolioRecord.setClosePrice(new BigDecimal(record.get(CLOSE)));
                portfolioRecord.setAdjustedClosePrice(new BigDecimal(record.get(ADJUSTED_CLOSE)));
                portfolioRecord.setVolume(Long.valueOf(record.get(VOLUME)));
                portfolioRecord.setQuantity(new BigDecimal(record.get(QUANTITY)));
                portfolioRecord.setMarketValue(new BigDecimal(record.get(MARKET_VALUE)));

                portfolioRecordList.add(portfolioRecord);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Processed {} records", portfolioRecordList.size());

        return portfolioRecordList;

    }
        @Override
    public List findByFileName(String name) {
        return null;
    }

    @Override
    public boolean uploadFile(PortfolioRecord o) {
        return false;
    }

    @Override
    public boolean uploadFiles(List<PortfolioRecord> list) {
        return false;
    }
}
