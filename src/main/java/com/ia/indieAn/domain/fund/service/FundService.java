package com.ia.indieAn.domain.fund.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public Slice<FundListDto> selectAllFund(FundSearchDto fundSearchDto){
        Pageable pageable = PageRequest.of(0, fundSearchDto.getPage(), Sort.by(fundSearchDto.getSort(), fundSearchDto.getSortValue()));
        Slice<FundListDto> fundListDtos = null;
        
        switch (fundSearchDto.getSearchValue()) {
            case "artist" -> {
                fundListDtos = fundRepository
                        .findByArtistKeywordFundList(pageable, fundSearchDto.getKeyword())
                        .map(FundListDto::convertToPage);
            }
            case "fundTitle" -> {
                fundListDtos = fundRepository
                        .findByTitleKeywordFundList(pageable, fundSearchDto.getKeyword())
                        .map(FundListDto::convertToPage);
            }
            case "fundContent" -> {
                fundListDtos = fundRepository
                        .findByContentKeywordFundList(pageable, fundSearchDto.getKeyword())
                        .map(FundListDto::convertToPage);
            }
            case "all" -> {
                fundListDtos = fundRepository
                        .findByAllKeywordFundList(pageable, fundSearchDto.getKeyword())
                        .map(FundListDto::convertToPage);
            }
        }
        return fundListDtos;
    }

    public Page<FundListDto> selectSoonFund(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "endDate"));
        return fundRepository.findSoonFundList(pageable).map(FundListDto::convertToPage);
    }

    //native query 없이 group by(sum) 사용
    //fund 엔티티 내부에 있는 rewardList도 rewardDtoList로 변환
    public FundDetailDto selectFundDetail(int fundNo){
        Fund fund = fundRepository.findByFundNo(fundNo)
                .orElseThrow(()->new CustomException(ErrorCode.FUND_NOT_FOUND));
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
        return fundRepository.findByFundNo(fundNo)
                .orElseThrow(()->new CustomException(ErrorCode.FUND_NOT_FOUND));
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

    @Transactional(rollbackFor = CustomException.class)
    public int enrollFund(FundEnrollDto fundEnrollDto) throws ParseException {
        Member member = userRepository.findByUserNo(fundEnrollDto.getUserNo());
        Fund fund = fundRepository.save(Fund.convertFormFundEnrollDto(fundEnrollDto, member));

        List<Reward> rewardList = fundEnrollDto.getReward().stream()
                .map(e->Reward.convertFromRewardDto(e, fund)).toList();
        rewardRepository.saveAll(rewardList);

        return fund.getFundNo();
    }
}
