package com.ia.indieAn.common.pageDto;

import com.ia.indieAn.common.pageDto.PageInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListDto {

    private Object listDto;
    private PageInfo pageinfo;


}
