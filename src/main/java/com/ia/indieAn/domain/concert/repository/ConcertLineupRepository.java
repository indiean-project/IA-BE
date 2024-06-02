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
            value = "select artist_name artistName,img_url imgUrl,A.artist_no artistNo\n" +
                    "from concert_lineup B\n" +
                    "left join(select artist_no, img_url\n" +
                    "from artist\n" +
                    "join img_url on (content_no = artist_no)\n" +
                    "where fabc_type ='A' and kc_type ='K') A on (A.artist_no = B.artist_no)\n"+
                    "where concert_no = :concertNo",
            nativeQuery = true

    )
    List<LineupPorjection> findByLinupList(@Param(value = "concertNo") int concertNo);
}
