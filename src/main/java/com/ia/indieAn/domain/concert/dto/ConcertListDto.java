package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.common.pageDto.PageInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Builder
@Getter
public class ConcertListDto {

    private ArrayList<ConcertDto> listDto;
    private PageInfo pageinfo;
}
