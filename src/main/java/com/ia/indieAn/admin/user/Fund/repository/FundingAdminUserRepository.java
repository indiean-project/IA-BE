package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface FundingAdminUserRepository extends JpaRepository<Fund, Integer> {

//    Fund findByFundNoAndFundStatus(int fundNo, String fundStatus);


    Optional<Fund> findByFundNo(int fundNo);

    ArrayList<Fund> findByFundTypeNo(FundTypeEnum fundTypeNo);
    ArrayList<Fund> findByFundStatus(String fundStatus);


    // 서치 기능 관련 리스트 뽑아오기
    ArrayList<Fund> findByFundTitleContaining(String fundTitle);
    ArrayList<Fund> findByMember(Member member);
}
