package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.domain.user.dto.UserBoardProjection;
import com.ia.indieAn.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserId(String userId);
    Member findByUserNo(int userNo);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    boolean existsByNicknameAndUserNoNot(String nickname, int userNo);
    boolean existsByPhone(String phone);
    boolean existsByPhoneAndUserNoNot(String phone, int userNo);
    Member findByNickname(String nickname);

    @Query(value=
            "select board_no as boardNo, board_title as boardTitle\n" +
                    ", (select count(*) from reply r where r.board_no = b.board_no) as replies\n" +
                    ", nickname as nickname, user_role as userRole\n " +
                    ", content_type_no as contentTypeNo\n" +
                    ", to_char(enroll_date, 'YYYY-MM-DD') as updateDate\n" +
                    ", view_count as viewCount\n" +
                    ", (select count(*) from content_like_log l where l.content_no = b.board_no and l.br_type = 'B') as likeCount\n" +
                    "from board b\n" +
                    "   join member m on (m.user_no = b.user_no)\n" +
                    "where m.user_no = :userNo",
            countQuery = "select count(*) from board",
            nativeQuery = true
    )
    List<UserBoardProjection> findUserBoardsByMemberUserNo(@Param(value="userNo") int userNo);
}