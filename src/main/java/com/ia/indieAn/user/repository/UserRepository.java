package com.ia.indieAn.user.repository;

import com.ia.indieAn.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Integer> {

    Member findByUserIdAndUserPwd(String userId, String userPwd);
}
