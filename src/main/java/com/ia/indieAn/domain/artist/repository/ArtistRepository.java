package com.ia.indieAn.domain.artist.repository;

import com.ia.indieAn.entity.artist.Artist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {



   Slice<Artist> findByArtistNameContaining(Pageable pageable, String name);
}
