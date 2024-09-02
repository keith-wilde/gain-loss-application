package com.kwilde.gainloss.repository;

import com.kwilde.gainloss.entity.PortfolioRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRecordRepository extends JpaRepository<PortfolioRecord, Long> {
}
