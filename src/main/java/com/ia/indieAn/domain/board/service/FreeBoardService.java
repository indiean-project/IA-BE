package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.board.dto.FreeBoardDto;
import com.ia.indieAn.domain.board.repository.FreeBoardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class FreeBoardService {

    @Autowired
    FreeBoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = CustomException.class)
    public Board boardEnroll(Board board) {
        Member member = userRepository.findByUserNo(board.getMember().getUserNo());
        board.setMember(member);
        return boardRepository.save(board);
    }

    public ArrayList<FreeBoardDto> freeBoardList() {
        ArrayList<Board> boardArrayList = (ArrayList<Board>) boardRepository.findAll();
        ArrayList<FreeBoardDto> boardDtoArrayList = new ArrayList<>();
        for (int i = 0 ; i < boardArrayList.size(); i++){
            boardDtoArrayList.add(new FreeBoardDto(boardArrayList.get(i)));
        }
        return boardDtoArrayList;
    }

}
