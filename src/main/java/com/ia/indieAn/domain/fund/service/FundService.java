package com.ia.indieAn.domain.fund.service;

import com.ia.indieAn.domain.fund.dto.*;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FundService {

    @Autowired
    FundRepository fundRepository;

    @Autowired
    OrderLogRepository orderLogRepository;

    public Page<FundListDto> selectAllFund(FundSearchDto fundSearchDto){
        Pageable pageable = null;
        System.out.println(fundSearchDto.getTitleKeyword());
        if (fundSearchDto.getSort().equals("ASC")){
            pageable = PageRequest.of(0, fundSearchDto.getPage(), Sort.by(Sort.Direction.ASC, fundSearchDto.getSortValue()));
        } else {
            pageable = PageRequest.of(0, fundSearchDto.getPage(), Sort.by(Sort.Direction.DESC, fundSearchDto.getSortValue()));
        }
        return fundRepository
                .findAllFundList(pageable, fundSearchDto.getTitleKeyword(), fundSearchDto.getContentKeyword(),fundSearchDto.getAllKeyword())
                .map(FundListDto::convertToPage);
    }

    public Page<FundListDto> selectSoonFund(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "endDate"));
        return fundRepository.findSoonFundList(pageable).map(FundListDto::convertToPage);
    }

    //native query 없이 group by(sum) 사용
    //fund 엔티티 내부에 있는 rewardList도 rewardDtoList로 변환
    public FundDetailDto selectFundDetail(int fundNo){
        Fund fund = fundRepository.findByFundNo(fundNo);
        return new FundDetailDto(   //매개변수(Fund, RewardListDto, OrderLog 엔티티의 totalPrice의 합계
                fund,
                fund.getRewardList().stream()
                        .map(RewardListDto::new)
                        .collect(Collectors.toList()),
                fund.getOrderLogList().stream()
                        .mapToInt(OrderLog::getTotalPrice).sum()
                );
    }
}
