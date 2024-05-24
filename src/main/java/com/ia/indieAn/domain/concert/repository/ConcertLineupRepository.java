package com.ia.indieAn.domain.concert.repository;


import com.ia.indieAn.domain.concert.dto.LineupPorjection;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertLineup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertLineupRepository extends JpaRepository<ConcertLineup, Integer> {



    @Query(
            value = "select artist_name artistName,img_url imgUrl,artist_no artistNo\n" +
                    "from concert_lineup\n" +
                    "left join(select content_no,img_url\n" +
                    "from img_url\n" +
                    "where fabc_type ='A' and kc_type ='K') on (content_no = concert_no)\n" +
                    "where concert_no = :concertNo",
            nativeQuery = true

    )
    List<LineupPorjection> findByLinupList(@Param(value = "concertNo") int concertNo);
}
