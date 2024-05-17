package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FundingAdminUserRepository extends JpaRepository<Fund, Integer> {

//    Fund findByFundNoAndFundStatus(int fundNo, String fundStatus);


    ArrayList<Fund> findByFundNoOrFundStatus(int fundNo, String fundStatus);


    ArrayList<Fund> findByFundTypeNoContainingAndFundStatusContainingAndFundTitleContaining(String fundTypeNo, String fundStatus, String fundTitle);



}
