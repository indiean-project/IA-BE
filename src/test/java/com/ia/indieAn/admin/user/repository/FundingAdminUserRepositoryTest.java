package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.admin.user.Fund.repository.FundingAdminUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FundingAdminUserRepositoryTest {

    @Autowired
    FundingAdminUserRepository adminUserRepository;

    @Test
    void searchTest() {
        System.out.println("test");
//        List<Fund> f = adminUserRepository.findByFundTypeNo(FundTypeEnum.valueOf("CONCERT"));
////        List<Fund> f = adminUserRepository.findByFundTypeNoContainingAndFundStatusContaining(FundTypeEnum.valueOf("CONCERT"), "Y");
//
//        for (Fund fund : f) {
//            System.out.println(fund.getFundTypeNo().getValue());
//        }
    }
}