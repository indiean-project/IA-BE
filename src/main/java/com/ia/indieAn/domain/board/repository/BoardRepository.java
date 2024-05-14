package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findAllByDeleteYn(Pageable pageable, String deleteYn);
    Page<Board> findAllByDeleteYnAndContentTypeNo(Pageable pageable, String deleteYn, ContentTypeEnum contentTypeNo);
}
