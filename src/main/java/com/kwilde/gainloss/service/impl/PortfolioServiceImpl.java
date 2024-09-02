package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.repository.PortfolioRecordRepository;
import com.kwilde.gainloss.service.FileService;
import com.kwilde.gainloss.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);

    private final FileService fileService;
    private final PortfolioRecordRepository portfolioRecordRepository;

    public PortfolioServiceImpl(FileService fileService, PortfolioRecordRepository portfolioRecordRepository) {
        this.fileService = fileService;
        this.portfolioRecordRepository = portfolioRecordRepository;
    }

    @Override
    public boolean importData() {
        List<PortfolioRecord> portfolioRecordList = fileService.parseAll();
        if(CollectionUtils.isEmpty(portfolioRecordList)){
            logger.info("No portfolio records found");
            return false;
        }

        portfolioRecordRepository.saveAll(portfolioRecordList);

        return true;
    }

    @Override
    public boolean importFromFile(String fileName) {
        List<PortfolioRecord> portfolioRecordList = fileService.parseByFileName(fileName);
        if(CollectionUtils.isEmpty(portfolioRecordList)){
            logger.info("No portfolio records found for file {}", fileName);
            return false;
        }

        portfolioRecordRepository.saveAll(portfolioRecordList);

        return true;
    }

    @Override
    public List<PortfolioRecord> findAll() {
        return portfolioRecordRepository.findAll();
    }
}
