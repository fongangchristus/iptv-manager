package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.PackagesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.Packages}.
 */
public interface PackagesService {

    /**
     * Save a packages.
     *
     * @param packagesDTO the entity to save.
     * @return the persisted entity.
     */
    PackagesDTO save(PackagesDTO packagesDTO);

    /**
     * Get all the packages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PackagesDTO> findAll(Pageable pageable);

    /**
     * Get all the packages with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<PackagesDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" packages.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PackagesDTO> findOne(Long id);

    /**
     * Delete the "id" packages.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
