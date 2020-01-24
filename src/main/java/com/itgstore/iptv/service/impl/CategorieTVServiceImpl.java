package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.CategorieTVService;
import com.itgstore.iptv.domain.CategorieTV;
import com.itgstore.iptv.repository.CategorieTVRepository;
import com.itgstore.iptv.service.dto.CategorieTVDTO;
import com.itgstore.iptv.service.mapper.CategorieTVMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieTV}.
 */
@Service
@Transactional
public class CategorieTVServiceImpl implements CategorieTVService {

    private final Logger log = LoggerFactory.getLogger(CategorieTVServiceImpl.class);

    private final CategorieTVRepository categorieTVRepository;

    private final CategorieTVMapper categorieTVMapper;

    public CategorieTVServiceImpl(CategorieTVRepository categorieTVRepository, CategorieTVMapper categorieTVMapper) {
        this.categorieTVRepository = categorieTVRepository;
        this.categorieTVMapper = categorieTVMapper;
    }

    /**
     * Save a categorieTV.
     *
     * @param categorieTVDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategorieTVDTO save(CategorieTVDTO categorieTVDTO) {
        log.debug("Request to save CategorieTV : {}", categorieTVDTO);
        CategorieTV categorieTV = categorieTVMapper.toEntity(categorieTVDTO);
        categorieTV = categorieTVRepository.save(categorieTV);
        return categorieTVMapper.toDto(categorieTV);
    }

    /**
     * Get all the categorieTVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategorieTVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieTVS");
        return categorieTVRepository.findAll(pageable)
            .map(categorieTVMapper::toDto);
    }


    /**
     * Get one categorieTV by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieTVDTO> findOne(Long id) {
        log.debug("Request to get CategorieTV : {}", id);
        return categorieTVRepository.findById(id)
            .map(categorieTVMapper::toDto);
    }

    /**
     * Delete the categorieTV by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieTV : {}", id);
        categorieTVRepository.deleteById(id);
    }
}
