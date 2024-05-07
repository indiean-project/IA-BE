package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
