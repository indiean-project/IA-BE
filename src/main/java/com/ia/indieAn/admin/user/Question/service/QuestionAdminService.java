package com.ia.indieAn.admin.user.Question.service;


import com.ia.indieAn.admin.user.Question.dto.QuestionAdminDto;
import com.ia.indieAn.admin.user.Question.dto.QuestionAdminSearchDto;
import com.ia.indieAn.admin.user.Question.repository.QuestionAdminRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.entity.user.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;


@Service
@Transactional(readOnly = true)
public class QuestionAdminService {

    @Autowired
    QuestionAdminRepository questionAdminRepository;

    @Autowired
    private UserRepository userRepository;

    public ArrayList<QuestionAdminDto> selectAllQuestionList(){
        ArrayList<Question> questionArrayList = (ArrayList<Question>) questionAdminRepository.findAll(Sort.by(Sort.Direction.ASC, "questionNo"));
        ArrayList<QuestionAdminDto> questionAdminDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<questionArrayList.size(); i++){
            questionAdminDtoArrayList.add(new QuestionAdminDto(questionArrayList.get(i)));
        }
        return questionAdminDtoArrayList;
    }
    @Transactional(rollbackFor = CustomException.class)
    public void updateQuestion(int QuestionNo, String ansContent){
        Question question = questionAdminRepository.findById(QuestionNo)
                .orElseThrow(() -> new CustomException(ErrorCode.FUND_NOT_FOUND));

        question.setAnsContent(ansContent);
        question.setAnsYn("Y");
        question.setAnsDate(Date.valueOf(LocalDate.now()));
        questionAdminRepository.save(question);


    }

    public ArrayList<QuestionAdminDto> searchQuestionList(QuestionAdminSearchDto searchDto){
        ArrayList<QuestionAdminDto> resultList = new ArrayList<>();

        ArrayList<Question> questionList = new ArrayList<>();
        if(searchDto.getSearchValue().equals("questionContent")) {
            questionList = questionAdminRepository.findByQuestionContentContaining(searchDto.getKeyword());
            for (Question question : questionList) {
                resultList.add(new QuestionAdminDto(question));
            }
        }else if (searchDto.getSearchValue().equals("questionNo")){
               Question question = questionAdminRepository.findByQuestionNo(Integer.parseInt(searchDto.getKeyword())).
                       orElseThrow( () -> new CustomException(ErrorCode.FUND_NOT_FOUND));
               resultList.add(new QuestionAdminDto(question));
        } else if (searchDto.getSearchValue().equals("userName")) {
                Member member = userRepository.findByUserName(searchDto.getKeyword());
                if (member != null) {
                    questionList = questionAdminRepository.findByMember_UserNo(member.getUserNo());
                    for (Question question : questionList) {
                        resultList.add(new QuestionAdminDto(question));
                    }
                }
            }
        return resultList;
        }




    }


