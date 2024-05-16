package com.ia.indieAn.domain.concert.repository;


import com.ia.indieAn.domain.concert.dto.ConcertProjection;
import com.ia.indieAn.entity.concert.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {

    Page<Concert> findByDeleteYnAndConcertTitleContaining(Pageable pageable, String deleteYn, String title);

    @Query(value =
            "select concert_no as concertNo, concert_title as concertTitle,location ,start_date as startDate, end_date as endDate ,TRUNC(start_date-sysdate) day \n" +
                    "from concert\n" +
                    "where end_date >sysdate\n" +
                    "and delete_yn = 'N'\n" +
                    "and concert_title like '%'|| :title ||'%'\n" +
                    "order by day asc",
            countQuery = "select count(*) from concert where end_date > sysdate and delete_yn = 'N' and concert_title like '%'|| :title ||'%'",
            nativeQuery = true
    )
    Page<ConcertProjection> findAllBySysDate(Pageable pageable, @Param(value = "title") String title);

    List<Concert> findByStartDateBetween(Date firstDate, Date lastDate);


    Concert findByConcertNo(int concertNo);
}
