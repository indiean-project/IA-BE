package com.ia.indieAn.admin.user.repository;

import com.ia.indieAn.entity.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityAdminRepository extends JpaRepository<Artist, Integer> {


}
