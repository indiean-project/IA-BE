package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import org.hibernate.type.descriptor.java.EnumJavaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FundingAdminUserRepositoryTest {

    @Autowired
    FundingAdminUserRepository adminUserRepository;

    @Test
    void searchTest() {
        List<Fund> f = adminUserRepository.findByFundTypeNo(FundTypeEnum.valueOf("CONCERT"));
//        List<Fund> f = adminUserRepository.findByFundTypeNoContainingAndFundStatusContaining(FundTypeEnum.valueOf("CONCERT"), "Y");

        for (Fund fund : f) {
            System.out.println(fund.getFundTypeNo().getValue());
        }
    }
}