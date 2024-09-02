package com.kwilde.gainloss.service;

import java.util.List;

public interface FileService<PortFolioRecord> {

    List<PortFolioRecord> findAll();

    List<PortFolioRecord> findByFileName(String name);
}
