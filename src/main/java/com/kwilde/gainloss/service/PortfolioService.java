package com.kwilde.gainloss.service;

import com.kwilde.gainloss.entity.PortfolioRecord;

import java.util.List;

public interface PortfolioService {

    boolean importData();

    List<PortfolioRecord> findAll();

}
