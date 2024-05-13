package com.ia.indieAn.domain.fund.service;

import com.ia.indieAn.domain.fund.dto.FundListByRevenueInterface;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FundService {

    @Autowired
    FundRepository fundRepository;

    @Autowired
    OrderLogRepository orderLogRepository;

    public Page<FundListDto> selectAllFund(String value, String sort, String standard){
        Pageable pageable = null;
        if (sort.equals("ASC")){
            pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.ASC, standard));
        } else {
            pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, standard));
        }


        return fundRepository.findAllFundList(pageable).map(FundListDto::convertToPage);
    }
}
