package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.CategorieVOD;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategorieVOD entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieVODRepository extends JpaRepository<CategorieVOD, Long> {

}
