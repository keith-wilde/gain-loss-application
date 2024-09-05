package com.kwilde.gainloss.controller;

import com.kwilde.gainloss.dto.PortfolioGainLoss;
import com.kwilde.gainloss.dto.TickerGainLoss;
import com.kwilde.gainloss.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/run/import")
    public ResponseEntity<Boolean> importData() {

        if (portfolioService.importData()) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/ticker/latest-vs-previous")
    public ResponseEntity<List<TickerGainLoss>> findLatestVsPreviousTickers() {
        return ResponseEntity.ok(portfolioService.getLatestVsPreviousTickers());
    }

    @GetMapping("/ticker/latest-vs-oldest")
    public ResponseEntity<List<TickerGainLoss>> findLatestVsOldestTickers() {
        return ResponseEntity.ok(portfolioService.getLatestVsOldestTickers());
    }

    @GetMapping("/latest-vs-previous")
    public ResponseEntity<List<PortfolioGainLoss>> findLatestVsPreviousPortfolios() {
        return ResponseEntity.ok(portfolioService.getLatestVsPreviousPortfolios());
    }

    @GetMapping("/latest-vs-oldest")
    public ResponseEntity<List<PortfolioGainLoss>> findLatestVsOldestPortfolios() {
        return ResponseEntity.ok(portfolioService.getLatestVsOldestPortfolios());
    }

    /**
     * @param portfolio - if multiple words, use underscore
     * @return List<TickerGainLoss>
     */
    @GetMapping("/{portfolio}/tickers/rank")
    public ResponseEntity<List<TickerGainLoss>> findRankOfTickersForPortfolio(@PathVariable String portfolio) {

        portfolio = portfolio.replace("_", " ");

        return ResponseEntity.ok(portfolioService.rankTickersWithinPortfolio(portfolio));
    }

    @GetMapping("/best-performance")
    public ResponseEntity<PortfolioGainLoss> findBestPerformingPortfolio() {
        Optional<PortfolioGainLoss> portfolioGainLossOptional = portfolioService.findBestPerformingPortfolio();
        return ResponseEntity.ok(portfolioGainLossOptional.orElse(new PortfolioGainLoss()));
    }

}
