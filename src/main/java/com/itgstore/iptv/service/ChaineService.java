package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.ChaineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.Chaine}.
 */
public interface ChaineService {

    /**
     * Save a chaine.
     *
     * @param chaineDTO the entity to save.
     * @return the persisted entity.
     */
    ChaineDTO save(ChaineDTO chaineDTO);

    /**
     * Get all the chaines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChaineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chaine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChaineDTO> findOne(Long id);

    /**
     * Delete the "id" chaine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
