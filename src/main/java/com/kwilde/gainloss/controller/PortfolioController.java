package com.kwilde.gainloss.controller;

import com.kwilde.gainloss.service.FileService;
import com.kwilde.gainloss.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final FileService fileService;

    public PortfolioController(PortfolioService portfolioService, FileService fileService) {
        this.portfolioService = portfolioService;
        this.fileService = fileService;
    }


    @GetMapping("/load")
    public ResponseEntity loadData(){
        fileService.findAll();

        return ResponseEntity.ok("here");
    }



}
