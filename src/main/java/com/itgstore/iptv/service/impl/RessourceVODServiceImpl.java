package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.RessourceVODService;
import com.itgstore.iptv.domain.RessourceVOD;
import com.itgstore.iptv.repository.RessourceVODRepository;
import com.itgstore.iptv.service.dto.RessourceVODDTO;
import com.itgstore.iptv.service.mapper.RessourceVODMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RessourceVOD}.
 */
@Service
@Transactional
public class RessourceVODServiceImpl implements RessourceVODService {

    private final Logger log = LoggerFactory.getLogger(RessourceVODServiceImpl.class);

    private final RessourceVODRepository ressourceVODRepository;

    private final RessourceVODMapper ressourceVODMapper;

    public RessourceVODServiceImpl(RessourceVODRepository ressourceVODRepository, RessourceVODMapper ressourceVODMapper) {
        this.ressourceVODRepository = ressourceVODRepository;
        this.ressourceVODMapper = ressourceVODMapper;
    }

    /**
     * Save a ressourceVOD.
     *
     * @param ressourceVODDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RessourceVODDTO save(RessourceVODDTO ressourceVODDTO) {
        log.debug("Request to save RessourceVOD : {}", ressourceVODDTO);
        RessourceVOD ressourceVOD = ressourceVODMapper.toEntity(ressourceVODDTO);
        ressourceVOD = ressourceVODRepository.save(ressourceVOD);
        return ressourceVODMapper.toDto(ressourceVOD);
    }

    /**
     * Get all the ressourceVODS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RessourceVODDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RessourceVODS");
        return ressourceVODRepository.findAll(pageable)
            .map(ressourceVODMapper::toDto);
    }


    /**
     * Get one ressourceVOD by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RessourceVODDTO> findOne(Long id) {
        log.debug("Request to get RessourceVOD : {}", id);
        return ressourceVODRepository.findById(id)
            .map(ressourceVODMapper::toDto);
    }

    /**
     * Delete the ressourceVOD by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RessourceVOD : {}", id);
        ressourceVODRepository.deleteById(id);
    }
}
