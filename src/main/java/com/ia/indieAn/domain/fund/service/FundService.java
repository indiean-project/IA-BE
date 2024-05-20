package com.ia.indieAn.domain.fund.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.fund.dto.*;
import com.ia.indieAn.domain.fund.repository.FundLogRepository;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.FundLog;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.entity.fund.Reward;
import com.ia.indieAn.entity.user.Member;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    RewardRepository rewardRepository;
    @Autowired
    FundLogRepository fundLogRepository;

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

    public Fund selectFund(int fundNo){
        return fundRepository.findByFundNo(fundNo);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void insertOrderLog(Fund fund, OrderReserveDto orderReserveDto, String billingKey){
        Member member = userRepository.findByUserNo(orderReserveDto.getUserNo());
        OrderLog orderLog = new OrderLog();
        orderLog.setMember(member);
        orderLog.setFund(fund);
        orderLog.setTotalPrice(orderReserveDto.getTotalPrice());
        orderLog.setReceiptId(orderReserveDto.getReceiptId());
        orderLog.setBillingKey(billingKey);
        orderLog.setPaymentDate(orderReserveDto.getPaymentDate());
        orderLogRepository.save(orderLog);

        List<FundLog> fundLogList = orderReserveDto.getReward().stream()
                .map(e->FundLog.builder()
                        .member(member)
                        .fund(fund)
                        .reward(rewardRepository.findByRewardNo(e.getRewardNo()))
                        .rewardAmount(e.getAmount())
                        .build()).toList();
        fundLogRepository.saveAll(fundLogList);
    }
}
