package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.Chaine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Chaine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChaineRepository extends JpaRepository<Chaine, Long> {

}
