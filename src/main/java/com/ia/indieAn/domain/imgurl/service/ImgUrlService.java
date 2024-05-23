package com.ia.indieAn.domain.imgurl.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.entity.board.ImgUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)

public class ImgUrlService {

    @Autowired
    ImgUrlRepository imgUrlRepository;

    @Transactional(rollbackFor = CustomException.class)
    public ImgUrl imgEnroll(ImgUrl imgUrl) {

       return imgUrlRepository.save(imgUrl);
    }
}
