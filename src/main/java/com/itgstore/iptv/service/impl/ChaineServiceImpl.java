package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.ChaineService;
import com.itgstore.iptv.domain.Chaine;
import com.itgstore.iptv.repository.ChaineRepository;
import com.itgstore.iptv.service.dto.ChaineDTO;
import com.itgstore.iptv.service.mapper.ChaineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Chaine}.
 */
@Service
@Transactional
public class ChaineServiceImpl implements ChaineService {

    private final Logger log = LoggerFactory.getLogger(ChaineServiceImpl.class);

    private final ChaineRepository chaineRepository;

    private final ChaineMapper chaineMapper;

    public ChaineServiceImpl(ChaineRepository chaineRepository, ChaineMapper chaineMapper) {
        this.chaineRepository = chaineRepository;
        this.chaineMapper = chaineMapper;
    }

    /**
     * Save a chaine.
     *
     * @param chaineDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ChaineDTO save(ChaineDTO chaineDTO) {
        log.debug("Request to save Chaine : {}", chaineDTO);
        Chaine chaine = chaineMapper.toEntity(chaineDTO);
        chaine = chaineRepository.save(chaine);
        return chaineMapper.toDto(chaine);
    }

    /**
     * Get all the chaines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChaineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chaines");
        return chaineRepository.findAll(pageable)
            .map(chaineMapper::toDto);
    }


    /**
     * Get one chaine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChaineDTO> findOne(Long id) {
        log.debug("Request to get Chaine : {}", id);
        return chaineRepository.findById(id)
            .map(chaineMapper::toDto);
    }

    /**
     * Delete the chaine by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chaine : {}", id);
        chaineRepository.deleteById(id);
    }
}
