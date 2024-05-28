package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.BoardProjection;
import com.ia.indieAn.domain.board.dto.BoardSideBarProjection;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findAllByDeleteYnAndContentTypeNo(Pageable pageable, String deleteYn, ContentTypeEnum contentTypeNo);

    @Query(value =
            "select distinct b.board_no as boardNo, board_title as boardTitle, board_content as boardContent\n" +
                    "                    , (select count(*) from reply r where r.board_no = b.board_no) as replies\n" +
                    "                    , m.user_no as userNo, nickname as nickname, user_role as userRole, to_char(enroll_date, 'YYYY-MM-DD') as enrollDate, to_char(update_date, 'YYYY-MM-DD') as updateDate, view_count as viewCount\n" +
                    "                    , (select count(*) from content_like_log l where l.content_no = b.board_no and l.br_type = 'B' and l.like_yn = 'Y') as likeCount\n" +
                    "                    , colo_no as coloNo\n" +
                    "                    , col_left_title as colLeftTitle, col_right_title as colRightTitle\n" +
                    "                    , (select count(*) from colo_log cl where vote = 'L' and cancel_yn = 'N' and c.colo_no = cl.colo_no ) as colLeftCount\n" +
                    "                    , (select count(*) from colo_log cl where vote = 'R' and cancel_yn = 'N' and c.colo_no = cl.colo_no ) as colRightCount\n" +
                    "                    , img_url as imgUrl\n" +
                    "                    from board b\n" +
                    "                    join member m on (m.user_no = b.user_no)\n" +
                    "                    left join board_colo c on (b.board_no = c.board_no)\n" +
                    "                    left join (select img_url, content_no from img_url where fabc_type = 'B' and kc_type = 'C') u on (b.board_no = u.content_no)\n" +
                    "                    where b.delete_yn = 'N'\n" +
                    "                    and content_type_no = :contentType\n" +
                    "                    and board_title like '%' || :title || '%'",
            countQuery = "select count(*) from board where content_type_no = :contentType and delete_yn = 'N' and board_title like '%' || :title || '%'",
            nativeQuery = true
    )
    Page<BoardProjection> findAll(Pageable pageable, @Param(value = "contentType") int contentTypeEnum, @Param(value = "title") String title);

    Board findByBoardNo(int boardNo);
    int countByContentTypeNoAndDeleteYn(ContentTypeEnum contentTypeEnum, String deleteYn);

    @Query(value =
            "select board_no as boardNo, board_title as boardTitle, replies as replies\n" +
                    "from\n" +
                    "(select board_no, board_title, delete_yn\n" +
                    "    , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                    "    , view_count\n" +
                    "    , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                    "    , enroll_date\n" +
                    "from board b\n" +
                    "where enroll_date > sysdate - 7\n" +
                    "and (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') >= 10 and delete_yn = 'N' and content_type_no = :contentType\n" +
                    "order by view_count desc)\n" +
                    "where rownum <= 5",
            countQuery = "select count(*)\n" +
                    "from\n" +
                    "(select board_no, board_title, delete_yn\n" +
                    "    , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                    "    , view_count\n" +
                    "    , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                    "    , enroll_date\n" +
                    "from board b\n" +
                    "where enroll_date > sysdate - 7\n" +
                    "and (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') >= 10 and delete_yn = 'N' and content_type_no = :contentType\n" +
                    "order by view_count desc)\n" +
                    "where rownum <= 5",
            nativeQuery = true
    )
    ArrayList<BoardSideBarProjection> findAllView(@Param(value = "contentType") int contentType);

    @Query(value =
            "select board_no as boardNo, board_title as boardTitle, replies as replies from\n" +
                    "(select board_no, board_title, delete_yn\n" +
                    "    , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                    "    , view_count\n" +
                    "    , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                    "    , enroll_date\n" +
                    "from board b\n" +
                    "where enroll_date > sysdate - 7 and delete_yn = 'N' and content_type_no = :contentType\n" +
                    "order by likeCount desc)\n" +
                    "where rownum <= 5",
            countQuery = "select count(*) from\n" +
                    "(select board_no, board_title, delete_yn\n" +
                    "    , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                    "    , view_count\n" +
                    "    , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                    "    , enroll_date\n" +
                    "from board b\n" +
                    "where enroll_date > sysdate - 7 and delete_yn = 'N' and content_type_no = :contentType\n" +
                    "order by likeCount desc)\n" +
                    "where rownum <= 5",
            nativeQuery = true
    )
    ArrayList<BoardSideBarProjection> findAllLike(@Param(value = "contentType") int contentType);

    @Query(value =
        "select board_no as boardNo, b.user_no as userNo, nickname as nickName\n" +
                "    , enroll_date as enrollDate, update_date as updateDate, board_title as boardTitle\n" +
                "    , board_content as boardContent, view_count as viewCount, user_role as userRole\n" +
                "    , (select count(*) from content_like_log l where b.board_no = l.content_no and br_type = 'B' and like_yn = 'Y') as likeCount\n" +
                "    , (select count(*) from reply r where b.board_no = r.board_no and delete_yn = 'N') replies\n" +
                "from board b\n" +
                "join member m on (b.user_no = m.user_no)\n" +
                "where b.board_no = :boardNo and b.delete_yn = 'N'",
            nativeQuery = true
    )
    Optional<BoardProjection> findDetail(@Param(value = "boardNo") int boardNo);
}
