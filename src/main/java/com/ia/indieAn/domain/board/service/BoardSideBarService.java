package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.domain.board.dto.BoardSideBarDto;
import com.ia.indieAn.domain.board.dto.BoardSideBarProjection;
import com.ia.indieAn.domain.board.dto.SideBarListDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BoardSideBarService {

    @Autowired
    BoardRepository boardRepository;

    public SideBarListDto sideBarList(String contentType) {
        int contentTypeNo = 0;
        if (contentType.equals("자유게시판")) {
            contentTypeNo = 1;
        } else if (contentType.equals("아티스트 자랑")) {
            contentTypeNo = 2;
        } else if (contentType.equals("콜로세움")) {
            contentTypeNo = 3;
        }

        ArrayList<BoardSideBarProjection> viewList = boardRepository.findAllView(contentTypeNo);
        ArrayList<BoardSideBarDto> viewListDto = new ArrayList<>();

        ArrayList<BoardSideBarProjection> likeList = boardRepository.findAllLike(contentTypeNo);
        ArrayList<BoardSideBarDto> likeListDto = new ArrayList<>();

        for (int i = 0; i < viewList.size(); i++) {
            viewListDto.add(new BoardSideBarDto(viewList.get(i).getBoardNo(), viewList.get(i).getBoardTitle(), viewList.get(i).getReplies()));
            likeListDto.add(new BoardSideBarDto(likeList.get(i).getBoardNo(), likeList.get(i).getBoardTitle(), likeList.get(i).getReplies()));
        }

        SideBarListDto list = SideBarListDto.builder()
                .likeListDto(likeListDto)
                .viewListDto(viewListDto)
                .build();

        return list;
    }

}
