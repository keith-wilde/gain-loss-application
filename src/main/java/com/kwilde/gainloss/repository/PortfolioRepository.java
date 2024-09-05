package com.kwilde.gainloss.repository;

import com.kwilde.gainloss.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query(value =  "select max(date) " +
                    "from portfolio", nativeQuery = true)
    LocalDate findLatestDate();


    @Query(value =  "select min(date) " +
                    "from portfolio", nativeQuery = true)
    LocalDate findEarliestDate();


    @Query(value = "select distinct portfolio_name from portfolio", nativeQuery = true)
    List<String> findAllPortfolioNames();

    @Query(value = "select distinct p1.portfolio_name, p1.ticker, ((p1.market_value - p2.market_value) / p2.market_value) as  \"GainLoss\" " +
            "from portfolio p1\n" +
            "join portfolio p2 on p1.ticker = p2.ticker and p2.date = :endDate " +
            "where p1.date = :startDate ", nativeQuery = true)
    List<Object[]> findTickerGainLossByDate(@Param("startDate") LocalDate startDate, @Param("endDate")  LocalDate endDate);

    @Query(value = "select p1.portfolio_name, p1.ticker, ((p1.market_value - p2.market_value) / p2.market_value) as  \"GainLoss\" " +
            "from portfolio p1\n" +
            "join portfolio p2 on (p1.ticker = p2.ticker and p2.date = :endDate) and (p1.portfolio_name = :portfolio_name) " +
            "where p1.date = :startDate and p1.portfolio_name = p2.portfolio_name ", nativeQuery = true)
    List<Object[]> findPortfolioGainLossByDate(@Param("portfolio_name")  String portfolioName, @Param("startDate") LocalDate startDate,@Param("endDate")  LocalDate endDate);

}
