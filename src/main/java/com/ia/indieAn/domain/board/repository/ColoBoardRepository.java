package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.BoardSideBarProjection;
import com.ia.indieAn.entity.board.BoardColo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ColoBoardRepository extends JpaRepository<BoardColo, Integer> {
    ArrayList<BoardColo> findAll();
    BoardColo findByColoNo(int coloNo);

    @Query(value =
        "select board_no as boardNo, board_title as boardTitle, replies as replies\n" +
                "                    from\n" +
                "                    (select board_no, board_title, delete_yn\n" +
                "                        , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                "                        , view_count\n" +
                "                        , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                "                        , enroll_date\n" +
                "                        , (select count(*) from colo_log where colo_no = b.board_no) coloCount\n" +
                "                    from board b\n" +
                "                    where enroll_date > now() - interval '7 days'\n" +
                "                    and (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') >= 10 and delete_yn = 'N' and content_type_no = :contentType\n" +
                "                    order by coloCount desc) A\n" +
                "                    limit 5",
            countQuery = "select count(*)\n" +
                    "    from\n" +
                    "    (select board_no, board_title, delete_yn\n" +
                    "        , (select count(*) from reply r where r.board_no = b.board_no and delete_yn = 'N') as replies\n" +
                    "        , view_count\n" +
                    "        , (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') as likeCount\n" +
                    "        , enroll_date\n" +
                    "        , (select count(*) from colo_log where colo_no = b.board_no) coloCount\n" +
                    "    from board b\n" +
                    "    where enroll_date > now() - interval '7 days'\n" +
                    "    and (select count(*) from content_like_log l where l.content_no = b.board_no and like_yn = 'Y') >= 10 and delete_yn = 'N' and content_type_no = 3\n" +
                    "    order by coloCount desc) A\n" +
                    "    limit 5",
            nativeQuery = true
    )
    ArrayList<BoardSideBarProjection> findAllColo(@Param(value = "contentType") int contentType);
}
