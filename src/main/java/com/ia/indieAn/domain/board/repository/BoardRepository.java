package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.BoardProjection;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findAllByDeleteYnAndContentTypeNo(Pageable pageable, String deleteYn, ContentTypeEnum contentTypeNo);

    @Query(value =
            "select board_no as boardNo, board_title as boardTitle, board_content as boardContent\n" +
                    "        , (select count(*) from reply r where r.board_no = b.board_no) as replies\n" +
                    "        , nickname as nickname, user_role as userRole, to_char(enroll_date, 'YYYY-MM-DD') as enrollDate, update_date as updateDate, view_count as viewCount\n" +
                    "        , (select count(*) from content_like_log l where l.content_no = b.board_no and l.br_type = 'B') as likeCount\n" +
                    "from board b\n" +
                    "    join member m on (m.user_no = b.user_no)\n" +
                    "where b.delete_yn = 'N'\n" +
                    "and content_type_no = :contentType\n" +
                    "and board_title like '%' || :title || '%'",
            countQuery = "select count(*) from board",
            nativeQuery = true
    )
    Page<BoardProjection> findAll(Pageable pageable, @Param(value = "contentType") int contentTypeEnum, @Param(value = "title") String title);

    Board findByBoardNo(int boardNo);
    int countByContentTypeNoAndDeleteYn(ContentTypeEnum contentTypeEnum, String deleteYn);
}
