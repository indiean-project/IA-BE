package com.ia.indieAn.admin.user.service;

import com.ia.indieAn.admin.user.dto.FundingAdminSearchDto;
import com.ia.indieAn.admin.user.dto.FundingAdminSearchOptionDto;
import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.repository.FundingAdminUserRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.FundStatusEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class FundingAdminService {

    @Autowired
    FundingAdminUserRepository fundingAdminUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    FundRepository fundRepository;

    // 모든 리스트 불러오기
    public ArrayList<FundingAdminUserDto> selectAllFundList(){
        ArrayList<Fund> fundArrayList = (ArrayList<Fund>) fundingAdminUserRepository.findAll(Sort.by(Sort.Direction.ASC, "FundNo"));
        ArrayList<FundingAdminUserDto> fundingAdminUserDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<fundArrayList.size(); i++){
            fundingAdminUserDtoArrayList.add(new FundingAdminUserDto(fundArrayList.get(i)));
        }
        return fundingAdminUserDtoArrayList;
    }
    // 권한 Status 변경 관련
    @Transactional(rollbackFor = CustomException.class)
    public void updateFundStatus(String fundStatus, int fundNo){
        Fund fund = fundingAdminUserRepository.findByFundNo(fundNo).
                orElseThrow( ()-> new CustomException(ErrorCode.FUND_NOT_FOUND));
        fund.setFundStatus(FundStatusEnum.valueOf(fundStatus));

    }

    // 검색 관련
    public ArrayList<FundingAdminUserDto> searchList(FundingAdminSearchDto searchDto){
       ArrayList<FundingAdminUserDto> resultList = new ArrayList<>();

        ArrayList<Fund> fundList = new ArrayList<>();
       if(searchDto.getSearchValue().equals("fundTitle")) {

           fundList = fundingAdminUserRepository.findByFundTitleContaining(searchDto.getKeyword());
           for (Fund f : fundList) {
               resultList.add(new FundingAdminUserDto(f));
           }
       }else if (searchDto.getSearchValue().equals("fundNo")){
           Fund fund = fundingAdminUserRepository.findByFundNo(Integer.parseInt(searchDto.getKeyword())).
                   orElseThrow( () -> new CustomException(ErrorCode.FUND_NOT_FOUND));

           resultList.add(new FundingAdminUserDto(fund));
       }else if (searchDto.getSearchValue().equals("artistName")){
           Member member = userRepository.findByUserName(searchDto.getKeyword());
            if (member != null){
                fundList = fundingAdminUserRepository.findByMember(member);
                for (Fund f : fundList) {
                    resultList.add(new FundingAdminUserDto(f));
                }
            }
       }
        return resultList;
 }
 // 정렬 처리하려다가 일단 말음
//  public Slice<Fund> selectAllFundList(FundingAdminSearchDto searchDto){
//        Pageable pageable = PageRequest.of(0, searchDto.getPage(), Sort.by(Sort.Direction.DESC, "FundNo"));
//      Slice<FundListDto> fundAdminListDto = null;
//
//      switch (searchDto.getSearchValue()) {
//          case "createDate"->{
//              fundAdminListDto = fundingAdminUserRepository
//                      .findByCreateDate
//          }
//      }
//    }
//




}
