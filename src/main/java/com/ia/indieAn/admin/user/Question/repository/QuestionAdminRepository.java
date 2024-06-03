package com.ia.indieAn.admin.user.Question.repository;


import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.entity.user.Question;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface QuestionAdminRepository extends JpaRepository<Question, Integer> {


 Optional<Question> findByQuestionNo(int questionNo);
 ArrayList<Question> findByQuestionContentContaining(String questionContent);
 ArrayList<Question> findByMember_UserNo(int userNo);


}
