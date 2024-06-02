package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteUserDto {

    private int userNo;
    private String deleteYn;

    public DeleteUserDto(Member member) {
        this.userNo = member.getUserNo();
        this.deleteYn = member.getDeleteYn();
    }

}
