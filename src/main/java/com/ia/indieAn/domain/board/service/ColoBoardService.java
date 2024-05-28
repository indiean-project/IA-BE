package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.BoardProjection;
import com.ia.indieAn.domain.board.dto.ColoBoardDto;
import com.ia.indieAn.domain.board.dto.ColoLogDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.domain.board.repository.ColoLogRepository;
import com.ia.indieAn.entity.board.BoardColo;
import com.ia.indieAn.entity.board.ColoLog;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ColoLogRepository coloLogRepository;

    @Transactional(rollbackFor = CustomException.class)
    public void coloBoardEnroll(BoardColo boardColo) {

        coloBoardRepository.save(boardColo);
    }

    public ListDto coloBoardList(Pageable pageable, String title) {
        Page<BoardProjection> pages = boardRepository.findAll(pageable, Integer.parseInt(ContentTypeEnum.COLO.getCode()), title);
        Page<ColoBoardDto> pagesDto = pages.map(ColoBoardDto::convertColoBoardDto);

        int totalPage = pagesDto.getTotalPages(); //전체 페이지 개수
        int currentPage = pagesDto.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pagesDto.getTotalElements(); //전체 테이블 건수
        int boardLimit = 5;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ListDto listDto  = ListDto.builder()
                .listDto(pagesDto.getContent())
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }

    @Transactional(rollbackFor = CustomException.class)
    public void voteEnroll(ColoLog coloLog) {
        ColoLog cl = coloLogRepository.findByBoardColo_ColoNoAndMember_UserNo(coloLog.getBoardColo().getColoNo(), coloLog.getMember().getUserNo());

        if(cl == null) {
            coloLogRepository.save(coloLog);
        } else {
            if(cl.getCancelYn().equals("Y")) {
                cl.setVote(coloLog.getVote());
                cl.setCancelYn("N");
            } else {
                cl.setCancelYn(cl.getVote().equals(coloLog.getVote()) ? "Y" : "N");
            }
            coloLogRepository.save(cl);
        }

    }

    public ArrayList<ColoLogDto> voteSelect(int userNo) {
        ArrayList<ColoLog> list = coloLogRepository.findByMember_UserNoAndCancelYn(userNo, "N");
        ArrayList<ColoLogDto> listDto = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            listDto.add(new ColoLogDto(list.get(i).getBoardColo().getColoNo(), list.get(i).getMember().getUserNo(), list.get(i).getCancelYn(), list.get(i).getVote()));
        }




        return listDto;
    }

    public List<ColoBoardDto> getWeeklyColoList(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "viewCount"));
        Page<BoardProjection> pages = boardRepository.findAll(pageable, Integer.parseInt(ContentTypeEnum.COLO.getCode()), "");

        return pages.map(ColoBoardDto::convertColoBoardDto).getContent();
    }
}
