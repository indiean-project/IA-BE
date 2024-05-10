package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.entity.fund.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface FundingAdminUserRepository extends JpaRepository<Fund, Integer> {

    Fund findByFundNoAndFundStatus(int fundNo, String fundStatus);



}
