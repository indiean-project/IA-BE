package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.board.repository.FreeBoardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContentLikeLogRepository contentLikeLogRepository;

    @Transactional(rollbackFor = CustomException.class)
    public Board boardEnroll(Board board) {
        Member member = userRepository.findByUserNo(board.getMember().getUserNo());
        board.setMember(member);
        return boardRepository.save(board);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void viewCount(int boardNo) {
        Board board = boardRepository.findByBoardNo(boardNo);
        board.setViewCount(board.getViewCount()+1);

        boardRepository.save(board);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void likeCount(ContentLikeLog contentLikeLog) {
        ContentLikeLog clike = contentLikeLogRepository.findByMember_UserNoAndContentNoAndBrType(contentLikeLog.getMember().getUserNo(), contentLikeLog.getContentNo(), contentLikeLog.getBrType());

        if(clike == null) {
            contentLikeLog.setLikeYn("Y");
            contentLikeLogRepository.save(contentLikeLog);
        } else if (clike.getLikeYn().equals("N")) {
            clike.setLikeYn("Y");
            contentLikeLogRepository.save(clike);
        } else {
            clike.setLikeYn("N");
            contentLikeLogRepository.save(clike);
        }

    }

    public ArrayList boardAmount() {
        ArrayList amountList = new ArrayList();

        int freeAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.FREE, "N");
        int proudAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.PROUD, "N");
        int coloAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.COLO, "N");

        amountList.add(freeAmount);
        amountList.add(proudAmount);
        amountList.add(coloAmount);

        return amountList;
    }

}
