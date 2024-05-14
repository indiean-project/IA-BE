package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.board.dto.ColoBoardDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ColoBoardService {

    @Autowired
    ColoBoardRepository coloBoardRepository;

    @Autowired
    BoardRepository boardRepository;

    @Transactional(rollbackFor = CustomException.class)
    public void coloBoardEnroll(BoardColo boardColo) {
        coloBoardRepository.save(boardColo);
    }

    public ArrayList<ColoBoardDto> coloBoardList(Pageable pageable, String deleteYn) {
        Page<Board> pages = boardRepository.findAllByDeleteYnAndContentTypeNo(pageable, deleteYn, ContentTypeEnum.COLO);
        List<Board> list = pages.getContent();

        ArrayList<ColoBoardDto> listDto = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getBoardColo() != null) {
                listDto.add(new ColoBoardDto(list.get(i)));
            }
        }

        log.info("listDto = {}", listDto);

        return listDto;
    }
}
