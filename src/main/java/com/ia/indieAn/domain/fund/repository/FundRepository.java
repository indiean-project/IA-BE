package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.domain.fund.dto.FundListByRevenueInterface;
import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;
import java.util.Optional;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    String generalFundQuery = "SELECT\n" +
            "            F.FUND_NO AS fundNo,\n" +
            "            target,\n" +
            "            NVL(O.REVENUE, 0) AS revenue,\n" +
            "            NVL(O.REVENUE/target, 0) AS rate,\n" +
            "            USER_NO AS userNo,\n" +
            "            FUND_TITLE AS fundTitle,\n" +
            "            START_DATE AS startDate,\n" +
            "            END_DATE AS endDate,\n" +
            "            FUND_INFO as fundInfo,\n" +
            "            FUND_TYPE_NO as fundTypeNo,\n" +
            "            FUND_DESCRIPTION as fundDescription,\n" +
            "            ARTIST_NAME AS artistName,\n" +
            "            IMG_URL AS imgUrl\n" +
            "        FROM\n" +
            "            FUND F          \n" +
            "        LEFT JOIN\n" +
            "            (SELECT FUND_NO, SUM(TOTAL_PRICE) AS REVENUE              \n" +
            "            FROM ORDER_LOG              \n" +
            "            GROUP BY\n" +
            "                FUND_NO) O                   \n" +
            "                ON (F.FUND_NO = O.FUND_NO)          \n" +
            "        LEFT JOIN ARTIST USING(USER_NO)\n" +
            "        LEFT JOIN (\n" +
            "                SELECT CONTENT_NO, IMG_URL FROM\n" +
            "                (SELECT CONTENT_NO, IMG_URL, ROW_NUMBER() OVER(PARTITION BY CONTENT_NO ORDER BY DBMS_RANDOM.VALUE) AS RN FROM IMG_URL \n" +
            "                WHERE KC_TYPE = 'K' AND FABC_TYPE = 'F')\n" +
            "                WHERE RN = 1\n" +
            "            ) ON (F.FUND_NO = CONTENT_NO)\n" +
            "        WHERE FUND_STATUS = 'A' \n";

    Optional<Fund> findByFundNo(int fundNo);


    @Query(
            value = "SELECT * FROM(" + generalFundQuery +
                    " AND\n" +
                    "                (FUND_TITLE LIKE '%' || :keyword || '%' \n" +
                    "                or FUND_INFO LIKE '%' || :keyword || '%'\n" +
                    "                or ARTIST_NAME LIKE '%' || :keyword || '%'))",
            countQuery = "SELECT COUNT(*) FROM ("+ generalFundQuery +")\n" +
                    " WHERE\n" +
                    "                fundTitle LIKE '%' || :keyword || '%' \n" +
                    "                or fundInfo LIKE '%' || :keyword || '%'\n" +
                    "                or artistName LIKE '%' || :keyword || '%'",
            nativeQuery = true
    )
    Slice<FundListByRevenueInterface> findByAllKeywordFundList(Pageable pageable, @Param(value = "keyword")String keyword);

    @Query(
            value = "SELECT * FROM(" + generalFundQuery +
                    " AND\n" +
                    "                FUND_TITLE LIKE '%' || :keyword || '%' )",
            countQuery = "SELECT COUNT(*) FROM ("+ generalFundQuery +")\n" +
                    " WHERE\n" +
                    "                fundTitle LIKE '%' || :keyword || '%' \n",
            nativeQuery = true
    )
    Slice<FundListByRevenueInterface> findByTitleKeywordFundList(Pageable pageable, @Param(value = "keyword")String keyword);

    @Query(
            value = "SELECT * FROM(" + generalFundQuery +
                    " AND\n" +
                    "                FUND_INFO LIKE '%' || :keyword || '%')",
            countQuery = "SELECT COUNT(*) FROM ("+ generalFundQuery +")\n" +
                    " WHERE\n" +
                    "                fundInfo LIKE '%' || :keyword || '%'",
            nativeQuery = true
    )
    Slice<FundListByRevenueInterface> findByContentKeywordFundList(Pageable pageable, @Param(value = "keyword")String keyword);

    @Query(
            value = "SELECT * FROM(" + generalFundQuery +
                    " AND\n" +
                    "                ARTIST_NAME LIKE '%' || :keyword || '%')",
            countQuery = "SELECT COUNT(*) FROM ("+ generalFundQuery +")\n" +
                    " WHERE\n" +
                    "                artistName LIKE '%' || :keyword || '%'",
            nativeQuery = true
    )
    Slice<FundListByRevenueInterface> findByArtistKeywordFundList(Pageable pageable, @Param(value = "keyword")String keyword);

    @Query(
            value = generalFundQuery +
                    "AND END_DATE >= SYSDATE",
            countQuery = "SELECT COUNT(*) FROM ("+ generalFundQuery +")\n" +
                    "WHERE endDate >= SYSDATE",
            nativeQuery = true
    )
    Page<FundListByRevenueInterface> findSoonFundList(Pageable pageable);



}
