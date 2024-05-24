package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.domain.user.dto.*;
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
                    "where m.user_no = :userNo\n" +
                    "order by updateDate desc",
            countQuery = "select count(*) from board",
            nativeQuery = true
    )
    List<UserBoardProjection> findUserBoardsByMemberUserNo(@Param(value="userNo") int userNo);

    @Query(value=
            "select f.fund_no as fundNo, fund_title as fundTitle, total_price as totalPrice\n" +
                    ", to_char(order_date, 'YYYY-MM-DD') as orderDate\n" +
                    ", to_char(ol.payment_date, 'YYYY-MM-DD') as paymentDate\n" +
                    "from fund f join order_log ol on (f.fund_no = ol.fund_no)\n" +
                    "where ol.user_no = :userNo order by orderDate desc",
            nativeQuery = true
    )
    List<UserFundOrderProjection> findUserFundOrderByOrderLogUserNo(@Param(value="userNo") int userNo);

    @Query(value=
            "select rw.reward_no as rewardNo, reward_name as rewardName, reward_price as rewardPrice\n" +
                    ", reward_amount as rewardAmount, (reward_amount*reward_price) as rewardTotalPrice\n" +
                    "from reward rw join fund_log fl on (rw.reward_no = fl.reward_no)\n" +
                    "where fl.user_no = :userNo and rw.fund_no = :fundNo",
            nativeQuery = true
    )
    List<UserRewardOrderProjection> findUserRewardOrderByUserNoAndFundNo(@Param(value="userNo") int userNo,
                                                                         @Param(value="fundNo") int fundNo);


}