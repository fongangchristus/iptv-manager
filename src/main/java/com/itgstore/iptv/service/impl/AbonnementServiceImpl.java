package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.AbonnementService;
import com.itgstore.iptv.domain.Abonnement;
import com.itgstore.iptv.repository.AbonnementRepository;
import com.itgstore.iptv.service.dto.AbonnementDTO;
import com.itgstore.iptv.service.mapper.AbonnementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Abonnement}.
 */
@Service
@Transactional
public class AbonnementServiceImpl implements AbonnementService {

    private final Logger log = LoggerFactory.getLogger(AbonnementServiceImpl.class);

    private final AbonnementRepository abonnementRepository;

    private final AbonnementMapper abonnementMapper;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository, AbonnementMapper abonnementMapper) {
        this.abonnementRepository = abonnementRepository;
        this.abonnementMapper = abonnementMapper;
    }

    /**
     * Save a abonnement.
     *
     * @param abonnementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AbonnementDTO save(AbonnementDTO abonnementDTO) {
        log.debug("Request to save Abonnement : {}", abonnementDTO);
        Abonnement abonnement = abonnementMapper.toEntity(abonnementDTO);
        abonnement = abonnementRepository.save(abonnement);
        return abonnementMapper.toDto(abonnement);
    }

    /**
     * Get all the abonnements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AbonnementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Abonnements");
        return abonnementRepository.findAll(pageable)
            .map(abonnementMapper::toDto);
    }


    /**
     * Get one abonnement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AbonnementDTO> findOne(Long id) {
        log.debug("Request to get Abonnement : {}", id);
        return abonnementRepository.findById(id)
            .map(abonnementMapper::toDto);
    }

    /**
     * Delete the abonnement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Abonnement : {}", id);
        abonnementRepository.deleteById(id);
    }
}
