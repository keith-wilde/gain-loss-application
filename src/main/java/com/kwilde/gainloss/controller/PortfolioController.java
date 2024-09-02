package com.kwilde.gainloss.controller;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PortfolioRecord>> loadData(){
        return ResponseEntity.ok(portfolioService.findAll());
    }

    @GetMapping("/run/import")
    public ResponseEntity<Boolean> importData(){

        if(portfolioService.importData()) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
