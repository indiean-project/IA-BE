package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    Fund findByFundNo(int fundNo);
}
