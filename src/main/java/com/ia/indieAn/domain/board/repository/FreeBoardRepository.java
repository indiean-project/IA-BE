package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
