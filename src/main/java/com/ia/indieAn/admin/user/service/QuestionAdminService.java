package com.ia.indieAn.admin.user.service;


import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.dto.QuestionAdminDto;
import com.ia.indieAn.admin.user.repository.QuestionAdminRepository;
import com.ia.indieAn.entity.user.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;



@Service
@Transactional(readOnly = true)
public class QuestionAdminService {

    @Autowired
    QuestionAdminRepository questionAdminRepository;

    public ArrayList<QuestionAdminDto> selectAllQuestionList(){
        ArrayList<Question> questionArrayList = (ArrayList<Question>) questionAdminRepository.findAll(Sort.by(Sort.Direction.ASC, "questionNo"));
        ArrayList<QuestionAdminDto> questionAdminDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<questionArrayList.size(); i++){
            questionAdminDtoArrayList.add(new QuestionAdminDto(questionArrayList.get(i)));
        }
        return questionAdminDtoArrayList;

    }

}
