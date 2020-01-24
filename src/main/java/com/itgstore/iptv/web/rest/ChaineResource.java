package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.service.ChaineService;
import com.itgstore.iptv.web.rest.errors.BadRequestAlertException;
import com.itgstore.iptv.service.dto.ChaineDTO;

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
 * REST controller for managing {@link com.itgstore.iptv.domain.Chaine}.
 */
@RestController
@RequestMapping("/api")
public class ChaineResource {

    private final Logger log = LoggerFactory.getLogger(ChaineResource.class);

    private static final String ENTITY_NAME = "chaine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChaineService chaineService;

    public ChaineResource(ChaineService chaineService) {
        this.chaineService = chaineService;
    }

    /**
     * {@code POST  /chaines} : Create a new chaine.
     *
     * @param chaineDTO the chaineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chaineDTO, or with status {@code 400 (Bad Request)} if the chaine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chaines")
    public ResponseEntity<ChaineDTO> createChaine(@Valid @RequestBody ChaineDTO chaineDTO) throws URISyntaxException {
        log.debug("REST request to save Chaine : {}", chaineDTO);
        if (chaineDTO.getId() != null) {
            throw new BadRequestAlertException("A new chaine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChaineDTO result = chaineService.save(chaineDTO);
        return ResponseEntity.created(new URI("/api/chaines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chaines} : Updates an existing chaine.
     *
     * @param chaineDTO the chaineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chaineDTO,
     * or with status {@code 400 (Bad Request)} if the chaineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chaineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chaines")
    public ResponseEntity<ChaineDTO> updateChaine(@Valid @RequestBody ChaineDTO chaineDTO) throws URISyntaxException {
        log.debug("REST request to update Chaine : {}", chaineDTO);
        if (chaineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChaineDTO result = chaineService.save(chaineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chaineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chaines} : get all the chaines.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chaines in body.
     */
    @GetMapping("/chaines")
    public ResponseEntity<List<ChaineDTO>> getAllChaines(Pageable pageable) {
        log.debug("REST request to get a page of Chaines");
        Page<ChaineDTO> page = chaineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chaines/:id} : get the "id" chaine.
     *
     * @param id the id of the chaineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chaineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chaines/{id}")
    public ResponseEntity<ChaineDTO> getChaine(@PathVariable Long id) {
        log.debug("REST request to get Chaine : {}", id);
        Optional<ChaineDTO> chaineDTO = chaineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chaineDTO);
    }

    /**
     * {@code DELETE  /chaines/:id} : delete the "id" chaine.
     *
     * @param id the id of the chaineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chaines/{id}")
    public ResponseEntity<Void> deleteChaine(@PathVariable Long id) {
        log.debug("REST request to delete Chaine : {}", id);
        chaineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
