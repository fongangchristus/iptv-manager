package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.CategorieTVDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.CategorieTV}.
 */
public interface CategorieTVService {

    /**
     * Save a categorieTV.
     *
     * @param categorieTVDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieTVDTO save(CategorieTVDTO categorieTVDTO);

    /**
     * Get all the categorieTVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieTVDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieTV.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieTVDTO> findOne(Long id);

    /**
     * Delete the "id" categorieTV.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
