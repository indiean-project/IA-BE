package com.ia.indieAn.domain.fund.service;

import com.ia.indieAn.domain.dto.FundListDto;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.entity.fund.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class FundService {

    @Autowired
    FundRepository fundRepository;

    public ArrayList<FundListDto> selectAllFund(){

        ArrayList<Fund> fundList = (ArrayList<Fund>) fundRepository.findAll();
        ArrayList<FundListDto> fundListDtos = new ArrayList<>();
        for (int i = 0; i < fundList.size(); i++) {
            fundListDtos.add(new FundListDto(fundList.get(i)));
        }
        return fundListDtos;
    }
}
