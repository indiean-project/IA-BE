package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Member;
import lombok.Data;

@Data
public class FindUserIdDto {
    private String userId;
    private String socialStatus;

    public FindUserIdDto(Member member){
        this.userId = member.getUserId();
        this.socialStatus = member.getSocialStatus();
    }
}
