package com.ia.indieAn.admin.user.Authority.service;

import com.ia.indieAn.admin.user.Authority.dto.AuthorityAdminDto;
import com.ia.indieAn.admin.user.Authority.repository.AuthorityAdminRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly=true)
@Slf4j
public class AuthorityAdminService {

    @Autowired
    AuthorityAdminRepository authorityAdminRepository;

    @Autowired
    UserRepository userRepository;
    public ArrayList<AuthorityAdminDto> selectAllAuthorityList(){
        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();
        log.info("size{}", authorityList.size());

        ArrayList<AuthorityAdminDto> authorityAdminDtoArrayList = new ArrayList<>();
        for(int i=0; i<authorityList.size(); i++){
            authorityAdminDtoArrayList.add(new AuthorityAdminDto(authorityList.get(i)));
        }

        return authorityAdminDtoArrayList;

    }

//    public ArrayList<AuthorityAdminDto> selectAllAuthorityTitle(){
//        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();
//
//        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityTitle = new ArrayList<>();
//        for(Artist artist : authorityList){
//            authorityAdminDtoAuthorityTitle.add(new AuthorityAdminDto(artist));
//        }
//        return authorityAdminDtoAuthorityTitle;
//
//    }
//
//    public ArrayList<AuthorityAdminDto> selectAllAuthorityStatus(){
//        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();
//
//        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityStatus = new ArrayList<>();
//        for(Artist artist : authorityList){
//            authorityAdminDtoAuthorityStatus.add(new AuthorityAdminDto(artist));
//        }
//        return authorityAdminDtoAuthorityStatus;
//
//
//    }
//
//    public ArrayList<AuthorityAdminDto> selectAllAuthorityType(){
//        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();
//
//        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityType = new ArrayList<>();
//        for(Artist artist : authorityList){
//            authorityAdminDtoAuthorityType.add(new AuthorityAdminDto(artist));
//        }
//        return authorityAdminDtoAuthorityType;
//
//    }

}
