package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.AbonnementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.Abonnement}.
 */
public interface AbonnementService {

    /**
     * Save a abonnement.
     *
     * @param abonnementDTO the entity to save.
     * @return the persisted entity.
     */
    AbonnementDTO save(AbonnementDTO abonnementDTO);

    /**
     * Get all the abonnements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AbonnementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" abonnement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbonnementDTO> findOne(Long id);

    /**
     * Delete the "id" abonnement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
