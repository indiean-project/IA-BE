package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.domain.fund.dto.FundListByRevenueInterface;
import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    String generalFundQuery = "SELECT F.FUND_NO AS fundNo, target, O.REVENUE AS revenue, O.REVENUE/target AS rate, USER_NO AS userNo, FUND_TITLE AS fundTitle, START_DATE AS startDate, END_DATE AS endDate, FUND_INFO as fundInfo, FUND_TYPE_NO as fundTypeNo, FUND_DESCRIPTION as fundDescription\n" +
            "FROM FUND F\n" +
            "JOIN (SELECT FUND_NO, SUM(TOTAL_PRICE) AS REVENUE\n" +
            "FROM ORDER_LOG\n" +
            "GROUP BY FUND_NO) O  ON (F.FUND_NO = O.FUND_NO)\n";
    Fund findByFundNo(int fundNo);

    @Query(
            value = generalFundQuery +
                    "WHERE FUND_TITLE LIKE '%' || :title || '%' AND FUND_INFO LIKE '%' || :content || '%' AND (FUND_TITLE LIKE '%' || :all || '%' OR FUND_INFO LIKE '%' || :all || '%')",
            countQuery = "SELECT COUNT(*) FROM FUND",
            nativeQuery = true
    )
    Page<FundListByRevenueInterface> findAllFundList(Pageable pageable, @Param(value = "title")String title, @Param(value = "content")String content, @Param(value = "all")String all);

    @Query(
            value = generalFundQuery +
                    "WHERE END_DATE >= SYSDATE",
            countQuery = "SELECT COUNT(*) FROM FUND",
            nativeQuery = true
    )
    Page<FundListByRevenueInterface> findSoonFundList(Pageable pageable);
}
