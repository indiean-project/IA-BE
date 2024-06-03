package com.ia.indieAn.admin.user.Authority.repository;

import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityAdminRepository extends JpaRepository<Artist, Integer> {


}
