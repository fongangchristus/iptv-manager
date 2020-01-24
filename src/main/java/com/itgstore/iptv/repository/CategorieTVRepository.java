package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.CategorieTV;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategorieTV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieTVRepository extends JpaRepository<CategorieTV, Long> {

}
