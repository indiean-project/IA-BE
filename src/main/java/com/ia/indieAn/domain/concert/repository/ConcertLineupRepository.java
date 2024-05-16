package com.ia.indieAn.domain.concert.repository;


import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertLineup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertLineupRepository extends JpaRepository<ConcertLineup, Integer> {


    List<ConcertLineup> findByConcert_ConcertNo(int concertNo);

}
