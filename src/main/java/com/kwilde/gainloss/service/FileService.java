package com.kwilde.gainloss.service;

import com.kwilde.gainloss.entity.Portfolio;

import java.util.List;

public interface FileService {

    List<Portfolio> parseAll();

    List<Portfolio> parseByFileName(String name);

}
