package com.ia.indieAn.admin.user.repository;


import com.ia.indieAn.entity.user.Question;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionAdminRepository extends JpaRepository<Question, Integer> {
}
