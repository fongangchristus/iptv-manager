package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.service.CategorieVODService;
import com.itgstore.iptv.web.rest.errors.BadRequestAlertException;
import com.itgstore.iptv.service.dto.CategorieVODDTO;

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
 * REST controller for managing {@link com.itgstore.iptv.domain.CategorieVOD}.
 */
@RestController
@RequestMapping("/api")
public class CategorieVODResource {

    private final Logger log = LoggerFactory.getLogger(CategorieVODResource.class);

    private static final String ENTITY_NAME = "categorieVOD";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieVODService categorieVODService;

    public CategorieVODResource(CategorieVODService categorieVODService) {
        this.categorieVODService = categorieVODService;
    }

    /**
     * {@code POST  /categorie-vods} : Create a new categorieVOD.
     *
     * @param categorieVODDTO the categorieVODDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieVODDTO, or with status {@code 400 (Bad Request)} if the categorieVOD has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-vods")
    public ResponseEntity<CategorieVODDTO> createCategorieVOD(@Valid @RequestBody CategorieVODDTO categorieVODDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieVOD : {}", categorieVODDTO);
        if (categorieVODDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieVOD cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieVODDTO result = categorieVODService.save(categorieVODDTO);
        return ResponseEntity.created(new URI("/api/categorie-vods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-vods} : Updates an existing categorieVOD.
     *
     * @param categorieVODDTO the categorieVODDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieVODDTO,
     * or with status {@code 400 (Bad Request)} if the categorieVODDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieVODDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-vods")
    public ResponseEntity<CategorieVODDTO> updateCategorieVOD(@Valid @RequestBody CategorieVODDTO categorieVODDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieVOD : {}", categorieVODDTO);
        if (categorieVODDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieVODDTO result = categorieVODService.save(categorieVODDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieVODDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-vods} : get all the categorieVODS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieVODS in body.
     */
    @GetMapping("/categorie-vods")
    public ResponseEntity<List<CategorieVODDTO>> getAllCategorieVODS(Pageable pageable) {
        log.debug("REST request to get a page of CategorieVODS");
        Page<CategorieVODDTO> page = categorieVODService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-vods/:id} : get the "id" categorieVOD.
     *
     * @param id the id of the categorieVODDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieVODDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-vods/{id}")
    public ResponseEntity<CategorieVODDTO> getCategorieVOD(@PathVariable Long id) {
        log.debug("REST request to get CategorieVOD : {}", id);
        Optional<CategorieVODDTO> categorieVODDTO = categorieVODService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieVODDTO);
    }

    /**
     * {@code DELETE  /categorie-vods/:id} : delete the "id" categorieVOD.
     *
     * @param id the id of the categorieVODDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-vods/{id}")
    public ResponseEntity<Void> deleteCategorieVOD(@PathVariable Long id) {
        log.debug("REST request to delete CategorieVOD : {}", id);
        categorieVODService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
