package com.ia.indieAn.admin.user.service;

import com.ia.indieAn.admin.user.dto.FundingAdminSearchOptionDto;
import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.repository.FundingAdminUserRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class FundingAdminService {

    @Autowired
    FundingAdminUserRepository fundingAdminUserRepository;

    public ArrayList<FundingAdminUserDto> selectAllFundList(){
        ArrayList<Fund> fundArrayList = (ArrayList<Fund>) fundingAdminUserRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        ArrayList<FundingAdminUserDto> fundingAdminUserDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<fundArrayList.size(); i++){
            fundingAdminUserDtoArrayList.add(new FundingAdminUserDto(fundArrayList.get(i)));
        }
        return fundingAdminUserDtoArrayList;
    }

    public ArrayList<FundingAdminUserDto> selectFundListByUserId(FundingAdminSearchOptionDto fo){
        ArrayList<Fund> fundSearchList = (ArrayList<Fund>) fundingAdminUserRepository.findByFundTypeNoContainingAndFundStatusContainingAndFundTitleContaining(fo.getType(), "Y" ,fo.getKeyword());
        ArrayList<FundingAdminUserDto> fundingAdminUserDtoArrayList = new ArrayList<>();
        for(int i=0; i<fundSearchList.size(); i++){
            fundingAdminUserDtoArrayList.add(new FundingAdminUserDto(fundSearchList.get(i)));
        }
        return fundingAdminUserDtoArrayList;
    }


}
