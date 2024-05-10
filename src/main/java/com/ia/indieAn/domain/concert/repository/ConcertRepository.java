package com.ia.indieAn.domain.concert.repository;


import com.ia.indieAn.entity.concert.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert,Integer> {
    Page<Concert> findAllByDeleteYn(Pageable pageable, String deleteYn);
}
