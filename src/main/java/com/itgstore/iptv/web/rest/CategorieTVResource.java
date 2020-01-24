package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.service.CategorieTVService;
import com.itgstore.iptv.web.rest.errors.BadRequestAlertException;
import com.itgstore.iptv.service.dto.CategorieTVDTO;

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
 * REST controller for managing {@link com.itgstore.iptv.domain.CategorieTV}.
 */
@RestController
@RequestMapping("/api")
public class CategorieTVResource {

    private final Logger log = LoggerFactory.getLogger(CategorieTVResource.class);

    private static final String ENTITY_NAME = "categorieTV";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieTVService categorieTVService;

    public CategorieTVResource(CategorieTVService categorieTVService) {
        this.categorieTVService = categorieTVService;
    }

    /**
     * {@code POST  /categorie-tvs} : Create a new categorieTV.
     *
     * @param categorieTVDTO the categorieTVDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieTVDTO, or with status {@code 400 (Bad Request)} if the categorieTV has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-tvs")
    public ResponseEntity<CategorieTVDTO> createCategorieTV(@Valid @RequestBody CategorieTVDTO categorieTVDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieTV : {}", categorieTVDTO);
        if (categorieTVDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieTV cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieTVDTO result = categorieTVService.save(categorieTVDTO);
        return ResponseEntity.created(new URI("/api/categorie-tvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-tvs} : Updates an existing categorieTV.
     *
     * @param categorieTVDTO the categorieTVDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieTVDTO,
     * or with status {@code 400 (Bad Request)} if the categorieTVDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieTVDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-tvs")
    public ResponseEntity<CategorieTVDTO> updateCategorieTV(@Valid @RequestBody CategorieTVDTO categorieTVDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieTV : {}", categorieTVDTO);
        if (categorieTVDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieTVDTO result = categorieTVService.save(categorieTVDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieTVDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-tvs} : get all the categorieTVS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieTVS in body.
     */
    @GetMapping("/categorie-tvs")
    public ResponseEntity<List<CategorieTVDTO>> getAllCategorieTVS(Pageable pageable) {
        log.debug("REST request to get a page of CategorieTVS");
        Page<CategorieTVDTO> page = categorieTVService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-tvs/:id} : get the "id" categorieTV.
     *
     * @param id the id of the categorieTVDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieTVDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-tvs/{id}")
    public ResponseEntity<CategorieTVDTO> getCategorieTV(@PathVariable Long id) {
        log.debug("REST request to get CategorieTV : {}", id);
        Optional<CategorieTVDTO> categorieTVDTO = categorieTVService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieTVDTO);
    }

    /**
     * {@code DELETE  /categorie-tvs/:id} : delete the "id" categorieTV.
     *
     * @param id the id of the categorieTVDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-tvs/{id}")
    public ResponseEntity<Void> deleteCategorieTV(@PathVariable Long id) {
        log.debug("REST request to delete CategorieTV : {}", id);
        categorieTVService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
