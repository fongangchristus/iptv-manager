package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.CategorieVODService;
import com.itgstore.iptv.domain.CategorieVOD;
import com.itgstore.iptv.repository.CategorieVODRepository;
import com.itgstore.iptv.service.dto.CategorieVODDTO;
import com.itgstore.iptv.service.mapper.CategorieVODMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieVOD}.
 */
@Service
@Transactional
public class CategorieVODServiceImpl implements CategorieVODService {

    private final Logger log = LoggerFactory.getLogger(CategorieVODServiceImpl.class);

    private final CategorieVODRepository categorieVODRepository;

    private final CategorieVODMapper categorieVODMapper;

    public CategorieVODServiceImpl(CategorieVODRepository categorieVODRepository, CategorieVODMapper categorieVODMapper) {
        this.categorieVODRepository = categorieVODRepository;
        this.categorieVODMapper = categorieVODMapper;
    }

    /**
     * Save a categorieVOD.
     *
     * @param categorieVODDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategorieVODDTO save(CategorieVODDTO categorieVODDTO) {
        log.debug("Request to save CategorieVOD : {}", categorieVODDTO);
        CategorieVOD categorieVOD = categorieVODMapper.toEntity(categorieVODDTO);
        categorieVOD = categorieVODRepository.save(categorieVOD);
        return categorieVODMapper.toDto(categorieVOD);
    }

    /**
     * Get all the categorieVODS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategorieVODDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieVODS");
        return categorieVODRepository.findAll(pageable)
            .map(categorieVODMapper::toDto);
    }


    /**
     * Get one categorieVOD by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieVODDTO> findOne(Long id) {
        log.debug("Request to get CategorieVOD : {}", id);
        return categorieVODRepository.findById(id)
            .map(categorieVODMapper::toDto);
    }

    /**
     * Delete the categorieVOD by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieVOD : {}", id);
        categorieVODRepository.deleteById(id);
    }
}
