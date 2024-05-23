package com.ia.indieAn.domain.artist.dto;

import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.artist.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class ArtistTest {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void artistSaveTest() {
        for(int i = 20; i <= 100; i++){
            Artist artist = new Artist();
            artist.setArtistName("아티스트"+i);
            artist.setMusicCategory("이런거 저런거");
            artist.setArtistInfo("<p>\n" +
                    "    반갑습니다.\n" +
                    "</p>\n" +
                    "<div>테스트용 html입니다.</div>\n" +
                    "<b>나는 입니다.</b>\n" +
                    "<ul>\n" +
                    "    <li>설명</li>\n" +
                    "    <li>설명</li>\n" +
                    "    <li>설명</li>\n" +
                    "    <li>설명?</li>\n" +
                    "    <li>설명?</li>\n" +
                    "    <li>설명?</li>\n" +
                    "    <li>설명?</li>\n" +
                    "</ul>");
            artist.setMember(userRepository.findByUserNo(i));
            artist.setDebutDate(Date.valueOf("2024-04-"+(i+1)));
            artist.setArtistStatus("Y");
            artistRepository.save(artist);
        }
    }
}
