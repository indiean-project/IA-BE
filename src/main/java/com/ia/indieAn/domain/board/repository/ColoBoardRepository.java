package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.BoardColo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ColoBoardRepository extends JpaRepository<BoardColo, Integer> {
    ArrayList<BoardColo> findAll();
    BoardColo findByColoNo(int coloNo);
}
