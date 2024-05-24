package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.NoticeProjection;
import com.ia.indieAn.entity.board.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    @Query(value =
            "select notice_no as noticeNo, nickname as nickName\n" +
                    "    , notice_title as noticeTitle, notice_content as noticeContent\n" +
                    "    , view_count as viewCount, to_char(enroll_Date, 'YYYY-MM-DD') as enrollDate\n" +
                    "    , to_char(update_Date, 'YYYY-MM-DD') as updateDate\n" +
                    "        from notice n\n" +
                    "        join member using (user_no)\n" +
                    "        where n.delete_yn = 'N'\n" +
                    "        and notice_title like '%' || :title || '%'",
            countQuery = "select count(*)\n" +
                    "    from notice n\n" +
                    "    join member using (user_no)\n" +
                    "    where n.delete_yn = 'N'\n" +
                    "    and notice_title like '%' || '' || '%'",
            nativeQuery = true
    )
    Page<NoticeProjection> findAll(Pageable pageable, String title);
}
