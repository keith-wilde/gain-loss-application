package com.kwilde.gainloss.service;

import com.kwilde.gainloss.entity.PortfolioRecord;

import java.util.List;

public interface FileService {

    List<PortfolioRecord> findAll();

    List<PortfolioRecord> findByFileName(String name);

    boolean uploadFile(PortfolioRecord portFolioRecord);

    boolean uploadFiles(List<PortfolioRecord> portFolioRecords);
}
