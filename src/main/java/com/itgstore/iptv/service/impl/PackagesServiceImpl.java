package com.itgstore.iptv.service.impl;

import com.itgstore.iptv.service.PackagesService;
import com.itgstore.iptv.domain.Packages;
import com.itgstore.iptv.repository.PackagesRepository;
import com.itgstore.iptv.service.dto.PackagesDTO;
import com.itgstore.iptv.service.mapper.PackagesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Packages}.
 */
@Service
@Transactional
public class PackagesServiceImpl implements PackagesService {

    private final Logger log = LoggerFactory.getLogger(PackagesServiceImpl.class);

    private final PackagesRepository packagesRepository;

    private final PackagesMapper packagesMapper;

    public PackagesServiceImpl(PackagesRepository packagesRepository, PackagesMapper packagesMapper) {
        this.packagesRepository = packagesRepository;
        this.packagesMapper = packagesMapper;
    }

    /**
     * Save a packages.
     *
     * @param packagesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PackagesDTO save(PackagesDTO packagesDTO) {
        log.debug("Request to save Packages : {}", packagesDTO);
        Packages packages = packagesMapper.toEntity(packagesDTO);
        packages = packagesRepository.save(packages);
        return packagesMapper.toDto(packages);
    }

    /**
     * Get all the packages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PackagesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Packages");
        return packagesRepository.findAll(pageable)
            .map(packagesMapper::toDto);
    }

    /**
     * Get all the packages with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PackagesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return packagesRepository.findAllWithEagerRelationships(pageable).map(packagesMapper::toDto);
    }
    

    /**
     * Get one packages by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PackagesDTO> findOne(Long id) {
        log.debug("Request to get Packages : {}", id);
        return packagesRepository.findOneWithEagerRelationships(id)
            .map(packagesMapper::toDto);
    }

    /**
     * Delete the packages by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Packages : {}", id);
        packagesRepository.deleteById(id);
    }
}
