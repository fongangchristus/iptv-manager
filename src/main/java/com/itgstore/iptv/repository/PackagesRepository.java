package com.itgstore.iptv.repository;

import com.itgstore.iptv.domain.Packages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Packages entity.
 */
@Repository
public interface PackagesRepository extends JpaRepository<Packages, Long> {

    @Query(value = "select distinct packages from Packages packages left join fetch packages.packagechaines left join fetch packages.packageVODS",
        countQuery = "select count(distinct packages) from Packages packages")
    Page<Packages> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct packages from Packages packages left join fetch packages.packagechaines left join fetch packages.packageVODS")
    List<Packages> findAllWithEagerRelationships();

    @Query("select packages from Packages packages left join fetch packages.packagechaines left join fetch packages.packageVODS where packages.id =:id")
    Optional<Packages> findOneWithEagerRelationships(@Param("id") Long id);

}
