package com.ia.indieAn.admin.user.service;

import com.ia.indieAn.admin.user.dto.AuthorityAdminDto;
import com.ia.indieAn.admin.user.repository.AuthorityAdminRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly=true)
public class AuthorityAdminService {

    @Autowired
    AuthorityAdminRepository authorityAdminRepository;

    @Autowired
    UserRepository userRepository;
    public ArrayList<AuthorityAdminDto> selectAllAuthorityList(){
        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();
//        System.out.println(authorityList);

        ArrayList<AuthorityAdminDto> authorityAdminDtoArrayList = new ArrayList<>();
        for(int i=0; i<authorityList.size(); i++){
//            Member m = userRepository.findByUserNo(authorityList.get(i).getMember().getUserNo());
            authorityAdminDtoArrayList.add(new AuthorityAdminDto(authorityList.get(i)));
        }
//        System.out.println(authorityAdminDtoArrayList);
        return authorityAdminDtoArrayList;

    }

    public ArrayList<AuthorityAdminDto> selectAllAuthorityTitle(){
        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();

        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityTitle = new ArrayList<>();
        for(Artist artist : authorityList){
            authorityAdminDtoAuthorityTitle.add(new AuthorityAdminDto(artist));
        }
        return authorityAdminDtoAuthorityTitle;

    }

    public ArrayList<AuthorityAdminDto> selectAllAuthorityStatus(){
        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();

        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityStatus = new ArrayList<>();
        for(Artist artist : authorityList){
            authorityAdminDtoAuthorityStatus.add(new AuthorityAdminDto(artist));
        }
        return authorityAdminDtoAuthorityStatus;


    }

    public ArrayList<AuthorityAdminDto> selectAllAuthorityType(){
        ArrayList<Artist> authorityList = (ArrayList<Artist>)authorityAdminRepository.findAll();

        ArrayList<AuthorityAdminDto> authorityAdminDtoAuthorityType = new ArrayList<>();
        for(Artist artist : authorityList){
            authorityAdminDtoAuthorityType.add(new AuthorityAdminDto(artist));
        }
        return authorityAdminDtoAuthorityType;

    }

}
