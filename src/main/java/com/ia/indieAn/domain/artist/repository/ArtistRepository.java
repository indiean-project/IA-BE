package com.ia.indieAn.domain.artist.repository;

import com.ia.indieAn.domain.artist.dto.ArtistDtoProjection;
import com.ia.indieAn.entity.artist.Artist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {

   String generalFundQuery ="select artist_no artistNo, artist_name artistName,debut_date debutDate,music_category musicCategory,img_url imgUrl,user_no userNo\n" +
           "from artist\n" +
           "left join (select img_url,content_no\n" +
           "from img_url\n" +
           "where fabc_type = 'A' and kc_type = 'K') on (content_no=artist_no)\n" +
           "where artist_name like '%'|| :name ||'%'\n" +
           "and artist.artist_status ='Y'\n";

   @Query(
           value = generalFundQuery +
                   "order by artist_no DESC",
           countQuery = "select count(*)\n"+
                   "from artist\n" +
                   "where artist_name like '%'|| :name ||'%'\n" +
                   "and artist.artist_status ='Y'",
           nativeQuery = true
   )
   Slice<ArtistDtoProjection> findByArtistListCreate(Pageable pageable, @Param(value = "name") String name);
   @Query(
           value = generalFundQuery +
                   "order by debut_date",
           countQuery = "select count(*)\n"+
                   "from artist\n" +
                   "where artist_name like '%'|| :name ||'%'\n" +
                   "and artist.artist_status ='Y'",
           nativeQuery = true
   )
   Slice<ArtistDtoProjection> findByArtistListDebut(Pageable pageable,@Param(value = "name") String keyword);

   Artist findByArtistNoAndArtistStatus(int artistNo, String y);
}
