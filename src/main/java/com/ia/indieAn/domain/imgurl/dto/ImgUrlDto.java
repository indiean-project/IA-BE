package com.ia.indieAn.domain.imgurl.dto;

import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import lombok.Data;

@Data
public class ImgUrlDto {
    private int contentNo;
    private String[] imgUrlList;
    private FabcTypeEnum fabcTypeEnum;
    private KcTypeEnum kcTypeEnum;
}
