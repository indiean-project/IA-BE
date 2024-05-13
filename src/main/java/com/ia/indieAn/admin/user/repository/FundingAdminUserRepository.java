package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FundingAdminUserRepository extends JpaRepository<Fund, Integer> {

//    Fund findByFundNoAndFundStatus(int fundNo, String fundStatus);


    ArrayList<Fund> findByFundNoOrFundStatus();


//    ArrayList<Fund> findByFundTypeNoAndFundStatusAndFundTitle(int fundTypeNo, String fundStatus, String fundTitle);


}
