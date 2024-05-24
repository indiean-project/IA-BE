package com.ia.indieAn.domain.imgurl.dto;

import com.ia.indieAn.entity.board.ImgUrl;
import lombok.Data;

@Data
public class ImgUrlListDto {

    private String imgUrl;

    public ImgUrlListDto(ImgUrl imgUrl){
        this.imgUrl = imgUrl.getImgUrl();
    }
}
