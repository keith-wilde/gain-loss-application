package com.kwilde.gainloss.service;

import com.kwilde.gainloss.entity.PortfolioRecord;

import java.util.List;

public interface FileService {

    List<PortfolioRecord> parseAll();

    List<PortfolioRecord> parseByFileName(String name);


}
