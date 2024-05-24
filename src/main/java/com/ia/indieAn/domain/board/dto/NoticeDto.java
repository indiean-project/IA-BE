package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Notice;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto {
    private int noticeNo;
    private String nickName;
    private String noticeTitle;
    private String noticeContent;
    private int viewCount;
    private String enrollDate;
    private String updateDate;

    public NoticeDto(Notice notice) {
        this.noticeNo = notice.getNoticeNo();
        this.nickName = notice.getMember().getUserName();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.viewCount = notice.getViewCount();
        this.enrollDate = String.valueOf(notice.getEnrollDate());
        this.updateDate = String.valueOf(notice.getUpdateDate());
    }

    @Override
    public String toString() {
        return "noticeNo = " + noticeNo
                + "nickName = " + nickName
                + "noticeTitle = " + noticeTitle
                + "noticeContent = " + noticeContent
                + "viewCount = " + viewCount
                + "enrollDate = " + enrollDate
                + "updateDate = " + updateDate;
    }

    public static NoticeDto convertNoticeDto(NoticeProjection np) {
        return builder()
                .noticeNo(np.getNoticeNo())
                .nickName(np.getNickName())
                .noticeTitle(np.getNoticeTitle())
                .noticeContent(np.getNoticeContent())
                .viewCount(np.getViewCount())
                .enrollDate(np.getEnrollDate())
                .updateDate(np.getUpdateDate())
                .build();
    }
}