package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.board.dto.FreeBoardDto;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.board.repository.FreeBoardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FreeBoardService {

    @Autowired
    FreeBoardRepository boardRepository;

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

    public ArrayList<FreeBoardDto> freeBoardList(Pageable pageable) {

        Page<Board> pages = boardRepository.findAll(pageable);
        List<Board> boardList = pages.getContent();

        ArrayList<FreeBoardDto> listDto = new ArrayList<>();

        for(int i = 0; i < boardList.size(); i++) {
            listDto.add(new FreeBoardDto(boardList.get(i)));
        }
        return listDto;
    }

    public int boardlike(int boardNo, BrTypeEnum brType, String likeYn) {
        return contentLikeLogRepository.countByContentNoAndBrTypeAndLikeYn(boardNo, brType, likeYn);
    }

}