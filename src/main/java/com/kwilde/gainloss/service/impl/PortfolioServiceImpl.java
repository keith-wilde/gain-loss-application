package com.kwilde.gainloss.service.impl;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);


    @Override
    public boolean importData() {
        return false;
    }

    @Override
    public List<PortfolioRecord> findAll() {
        return null;
    }
}
