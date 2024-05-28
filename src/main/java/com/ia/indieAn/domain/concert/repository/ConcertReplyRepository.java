package com.ia.indieAn.domain.concert.repository;

import com.ia.indieAn.entity.concert.ConcertLineup;
import com.ia.indieAn.entity.concert.ConcertReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ConcertReplyRepository extends JpaRepository<ConcertReply, Integer> {

    List<ConcertReply> findAllByConcert_ConcertNoAndDeleteYnOrderByConcertReplyNoDesc(int concertNo, String n);

    ConcertReply findByConcertReplyNo(int contentNo);
}

