package com.ia.indieAn.domain.imgurl.repository;

import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ImgUrlRepository extends JpaRepository<ImgUrl, Integer> {
    ArrayList<ImgUrl> findByContentNoAndFabcTypeAndKcType(int contentNo, FabcTypeEnum fabcTypeEnum, KcTypeEnum kcTypeEnum);
}
