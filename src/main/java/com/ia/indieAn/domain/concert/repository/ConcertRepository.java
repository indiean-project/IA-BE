package com.ia.indieAn.domain.concert.repository;


import com.ia.indieAn.entity.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert,Integer> {

}
