package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.entity.user.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByMember_UserNo(int userNo);
}
