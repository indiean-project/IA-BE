package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<Board, Integer> {
}
