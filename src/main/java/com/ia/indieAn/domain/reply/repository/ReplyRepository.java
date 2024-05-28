package com.ia.indieAn.domain.reply.repository;

import com.ia.indieAn.entity.board.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    ArrayList<Reply> findAllByBoard_BoardNoAndDeleteYnOrderByReplyNoDesc(int contentNo, String deleteYn);
    Reply findByReplyNo(int replyNo);
}
