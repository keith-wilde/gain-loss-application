package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.service.FileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Service
public class CSVFileService implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(CSVFileService.class);

   // TODO, enum for header values?
    String [] HEADERS = {"Portfolio", "Ticker", "Date",
            "Open", "High", "Low", "Close", "Adj Close", "Volume", "Quantity", "Market Value"};

//   TODO  @value for file location?


    @Override
    public List findAll() {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS);

        Reader in = null;
        try {
            in = new FileReader(ResourceUtils.getFile("classpath:data.csv"));
            Iterable<CSVRecord> records = csvFormat.parse(in);
            for (CSVRecord record : records) {
                String columnOne = record.get(0);
                String columnTwo = record.get(1);
//                logger.info("columnOne" + columnOne);
//                logger.info("columnTWO " + columnTwo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;

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
