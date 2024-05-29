package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.entity.user.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
