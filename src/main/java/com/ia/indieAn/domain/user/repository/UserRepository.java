package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserId(String userId);
    Member findByUserNo(int userNo);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    boolean existsByNicknameAndUserNoNot(String nickname, int userNo);
    boolean existsByPhone(String phone);
    boolean existsByPhoneAndUserNoNot(String phone, int userNo);
    Member findByNickname(String nickname);
}