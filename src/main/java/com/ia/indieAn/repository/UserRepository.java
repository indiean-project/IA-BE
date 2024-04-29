package com.ia.indieAn.repository;

import com.ia.indieAn.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Integer> {

    Member findByUserIdAndUserPwd(String userId, String userPwd);
}
