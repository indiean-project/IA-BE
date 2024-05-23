package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.domain.fund.dto.FundListByRevenueInterface;
import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;
import java.util.Optional;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    String generalFundQuery = "SELECT F.FUND_NO AS fundNo, target, NVL(O.REVENUE, 0) AS revenue, NVL(O.REVENUE/target, 0) AS rate, USER_NO AS userNo, FUND_TITLE AS fundTitle, START_DATE AS startDate, END_DATE AS endDate, FUND_INFO as fundInfo, FUND_TYPE_NO as fundTypeNo, FUND_DESCRIPTION as fundDescription\n" +
            "FROM FUND F\n" +
            "LEFT JOIN (SELECT FUND_NO, SUM(TOTAL_PRICE) AS REVENUE\n" +
            "FROM ORDER_LOG\n" +
            "GROUP BY FUND_NO) O  ON (F.FUND_NO = O.FUND_NO)\n";

    Optional<Fund> findByFundNo(int fundNo);


    @Query(
            value = generalFundQuery +
                    "WHERE FUND_TITLE LIKE '%' || :title || '%' AND FUND_INFO LIKE '%' || :content || '%' AND (FUND_TITLE LIKE '%' || :all || '%' AND FUND_INFO LIKE '%' || :all || '%')",
            nativeQuery = true
    )
    Page<FundListByRevenueInterface> findAllFundList(Pageable pageable, @Param(value = "title")String title, @Param(value = "content")String content, @Param(value = "all")String all);

    @Query(
            value = generalFundQuery +
                    "WHERE END_DATE >= SYSDATE",
            nativeQuery = true
    )
    Page<FundListByRevenueInterface> findSoonFundList(Pageable pageable);


}
