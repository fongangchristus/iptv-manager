package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.service.RessourceVODService;
import com.itgstore.iptv.web.rest.errors.BadRequestAlertException;
import com.itgstore.iptv.service.dto.RessourceVODDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.itgstore.iptv.domain.RessourceVOD}.
 */
@RestController
@RequestMapping("/api")
public class RessourceVODResource {

    private final Logger log = LoggerFactory.getLogger(RessourceVODResource.class);

    private static final String ENTITY_NAME = "ressourceVOD";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RessourceVODService ressourceVODService;

    public RessourceVODResource(RessourceVODService ressourceVODService) {
        this.ressourceVODService = ressourceVODService;
    }

    /**
     * {@code POST  /ressource-vods} : Create a new ressourceVOD.
     *
     * @param ressourceVODDTO the ressourceVODDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ressourceVODDTO, or with status {@code 400 (Bad Request)} if the ressourceVOD has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ressource-vods")
    public ResponseEntity<RessourceVODDTO> createRessourceVOD(@Valid @RequestBody RessourceVODDTO ressourceVODDTO) throws URISyntaxException {
        log.debug("REST request to save RessourceVOD : {}", ressourceVODDTO);
        if (ressourceVODDTO.getId() != null) {
            throw new BadRequestAlertException("A new ressourceVOD cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RessourceVODDTO result = ressourceVODService.save(ressourceVODDTO);
        return ResponseEntity.created(new URI("/api/ressource-vods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ressource-vods} : Updates an existing ressourceVOD.
     *
     * @param ressourceVODDTO the ressourceVODDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ressourceVODDTO,
     * or with status {@code 400 (Bad Request)} if the ressourceVODDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ressourceVODDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ressource-vods")
    public ResponseEntity<RessourceVODDTO> updateRessourceVOD(@Valid @RequestBody RessourceVODDTO ressourceVODDTO) throws URISyntaxException {
        log.debug("REST request to update RessourceVOD : {}", ressourceVODDTO);
        if (ressourceVODDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RessourceVODDTO result = ressourceVODService.save(ressourceVODDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ressourceVODDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ressource-vods} : get all the ressourceVODS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ressourceVODS in body.
     */
    @GetMapping("/ressource-vods")
    public ResponseEntity<List<RessourceVODDTO>> getAllRessourceVODS(Pageable pageable) {
        log.debug("REST request to get a page of RessourceVODS");
        Page<RessourceVODDTO> page = ressourceVODService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ressource-vods/:id} : get the "id" ressourceVOD.
     *
     * @param id the id of the ressourceVODDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ressourceVODDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ressource-vods/{id}")
    public ResponseEntity<RessourceVODDTO> getRessourceVOD(@PathVariable Long id) {
        log.debug("REST request to get RessourceVOD : {}", id);
        Optional<RessourceVODDTO> ressourceVODDTO = ressourceVODService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ressourceVODDTO);
    }

    /**
     * {@code DELETE  /ressource-vods/:id} : delete the "id" ressourceVOD.
     *
     * @param id the id of the ressourceVODDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ressource-vods/{id}")
    public ResponseEntity<Void> deleteRessourceVOD(@PathVariable Long id) {
        log.debug("REST request to delete RessourceVOD : {}", id);
        ressourceVODService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
