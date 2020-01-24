package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.RessourceVODDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.RessourceVOD}.
 */
public interface RessourceVODService {

    /**
     * Save a ressourceVOD.
     *
     * @param ressourceVODDTO the entity to save.
     * @return the persisted entity.
     */
    RessourceVODDTO save(RessourceVODDTO ressourceVODDTO);

    /**
     * Get all the ressourceVODS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RessourceVODDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ressourceVOD.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RessourceVODDTO> findOne(Long id);

    /**
     * Delete the "id" ressourceVOD.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
