package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.Abonnement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Abonnement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

}
