package com.ia.indieAn.domain.reply.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.reply.dto.ReplyDto;
import com.ia.indieAn.domain.reply.repository.ReplyRepository;
import com.ia.indieAn.entity.board.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    public ArrayList<ReplyDto> replyList(int contentNo) {
        ArrayList<Reply> list = replyRepository.findAllByBoard_BoardNoAndDeleteYnOrderByReplyNoDesc(contentNo, "N");
        ArrayList<ReplyDto> listDto = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            listDto.add(new ReplyDto(list.get(i).getBoard().getBoardNo()
                    , list.get(i).getCreateDate()
                    , list.get(i).getReplyNo()
                    , list.get(i).getMember().getNickname()
                    , list.get(i).getDeleteYn()
                    , list.get(i).getReplyContent()
                    , list.get(i).getMember().getUserNo()));
        }
        return listDto;
    }

    @Transactional(rollbackFor = CustomException.class)
    public void replyEnroll(Reply reply) {
        replyRepository.save(reply);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void replyDelete(int contentNo) {
        Reply reply = replyRepository.findByReplyNo(contentNo);
        reply.setDeleteYn("Y");
        replyRepository.save(reply);
    }

}
