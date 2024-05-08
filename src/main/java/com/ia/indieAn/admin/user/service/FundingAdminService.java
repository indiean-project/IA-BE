package com.ia.indieAn.admin.user.service;

import com.ia.indieAn.admin.user.dto.FundingUserDto;
import com.ia.indieAn.admin.user.repository.FundingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FundingService {

    @Autowired
    FundingUserRepository fundingUserRepository;

    public FundingUserDto fundingUser(Member member){

    }
}
