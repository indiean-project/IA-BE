package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSideBarDto {
    private int boardNo;
    private String boardTitle;
    private int replies;

    public BoardSideBarDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.boardTitle = board.getBoardTitle();
        this.replies = board.getReplies().size();
    }

    @Override
    public String toString() {
        return "boardNo = " + boardNo
                + "boardTitle = " + boardTitle
                + "replies = " + replies;
    }

    public static BoardSideBarDto convertBoardSideBarDto(BoardSideBarProjection bp) {
        return builder()
                .boardNo(bp.getBoardNo())
                .boardTitle(bp.getBoardTitle())
                .replies(bp.getReplies())
                .build();
    }
}
