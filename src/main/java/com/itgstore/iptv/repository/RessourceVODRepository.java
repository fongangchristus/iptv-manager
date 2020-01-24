package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.RessourceVOD;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RessourceVOD entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RessourceVODRepository extends JpaRepository<RessourceVOD, Long> {

}
