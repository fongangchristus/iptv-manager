package com.itgstore.iptv.service;

import com.itgstore.iptv.service.dto.CategorieVODDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.itgstore.iptv.domain.CategorieVOD}.
 */
public interface CategorieVODService {

    /**
     * Save a categorieVOD.
     *
     * @param categorieVODDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieVODDTO save(CategorieVODDTO categorieVODDTO);

    /**
     * Get all the categorieVODS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieVODDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieVOD.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieVODDTO> findOne(Long id);

    /**
     * Delete the "id" categorieVOD.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
