package com.kwilde.gainloss.controller;

import com.kwilde.gainloss.entity.PortfolioRecord;
import com.kwilde.gainloss.repository.PortfolioRepository;
import com.kwilde.gainloss.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired// TODO, remove temporary
    PortfolioRepository portfolioRepository;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/all") // TODO, remove temporary
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

    @GetMapping("/ticker/latest-vs-previous")
    public ResponseEntity findLatestVsPreviousTickers(){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ticker/latest-vs-oldest")
    public ResponseEntity findLatestVsOldestTickers(){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest-vs-previous")
    public ResponseEntity findLatestVsPreviousPortfolios(){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest-vs-oldest")
    public ResponseEntity findLatestVsOldestPortfolios(){

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{portfolio}/tickers/rank")
    public ResponseEntity findRankOfTickersForPortfolio(@PathVariable String portfolio){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/best-performance")
    public ResponseEntity findBestPerformingPortfolio(){
        return ResponseEntity.ok().build();
    }


    @GetMapping("/run/test") // TODO, remove. temporary
    public ResponseEntity test(){
        return ResponseEntity.ok(portfolioRepository
                .findPortfolioGainLossByDate("Fund One", LocalDate.of(2020,10,19), LocalDate.of(2020, 11, 02)));
    }

    @GetMapping("/run/test2") // TODO, remove. temporary
    public ResponseEntity test2(){
//        return ResponseEntity.ok(portfolioRecordRepository
//                .findTickerGainLossByDate(LocalDate.of(2020,10,19), LocalDate.of(2020, 11, 02)));

        return ResponseEntity.ok(portfolioService.getLatestVsPreviousPortfolios());
    }
}
