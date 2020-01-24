package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.RessourceVOD;
import com.itgstore.iptv.repository.RessourceVODRepository;
import com.itgstore.iptv.service.RessourceVODService;
import com.itgstore.iptv.service.dto.RessourceVODDTO;
import com.itgstore.iptv.service.mapper.RessourceVODMapper;
import com.itgstore.iptv.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static com.itgstore.iptv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RessourceVODResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class RessourceVODResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN_RESSOURCE_VOD = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_RESSOURCE_VOD = "BBBBBBBBBB";

    private static final String DEFAULT_PATH_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_PATH_LOGO = "BBBBBBBBBB";

    private static final UUID DEFAULT_CODE = UUID.randomUUID();
    private static final UUID UPDATED_CODE = UUID.randomUUID();

    private static final String DEFAULT_RESUME = "AAAAAAAAAA";
    private static final String UPDATED_RESUME = "BBBBBBBBBB";

    @Autowired
    private RessourceVODRepository ressourceVODRepository;

    @Autowired
    private RessourceVODMapper ressourceVODMapper;

    @Autowired
    private RessourceVODService ressourceVODService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRessourceVODMockMvc;

    private RessourceVOD ressourceVOD;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RessourceVODResource ressourceVODResource = new RessourceVODResource(ressourceVODService);
        this.restRessourceVODMockMvc = MockMvcBuilders.standaloneSetup(ressourceVODResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RessourceVOD createEntity(EntityManager em) {
        RessourceVOD ressourceVOD = new RessourceVOD()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .lienRessourceVOD(DEFAULT_LIEN_RESSOURCE_VOD)
            .pathLogo(DEFAULT_PATH_LOGO)
            .code(DEFAULT_CODE)
            .resume(DEFAULT_RESUME);
        return ressourceVOD;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RessourceVOD createUpdatedEntity(EntityManager em) {
        RessourceVOD ressourceVOD = new RessourceVOD()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .lienRessourceVOD(UPDATED_LIEN_RESSOURCE_VOD)
            .pathLogo(UPDATED_PATH_LOGO)
            .code(UPDATED_CODE)
            .resume(UPDATED_RESUME);
        return ressourceVOD;
    }

    @BeforeEach
    public void initTest() {
        ressourceVOD = createEntity(em);
    }

    @Test
    @Transactional
    public void createRessourceVOD() throws Exception {
        int databaseSizeBeforeCreate = ressourceVODRepository.findAll().size();

        // Create the RessourceVOD
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(ressourceVOD);
        restRessourceVODMockMvc.perform(post("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isCreated());

        // Validate the RessourceVOD in the database
        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeCreate + 1);
        RessourceVOD testRessourceVOD = ressourceVODList.get(ressourceVODList.size() - 1);
        assertThat(testRessourceVOD.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testRessourceVOD.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRessourceVOD.getLienRessourceVOD()).isEqualTo(DEFAULT_LIEN_RESSOURCE_VOD);
        assertThat(testRessourceVOD.getPathLogo()).isEqualTo(DEFAULT_PATH_LOGO);
        assertThat(testRessourceVOD.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRessourceVOD.getResume()).isEqualTo(DEFAULT_RESUME);
    }

    @Test
    @Transactional
    public void createRessourceVODWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ressourceVODRepository.findAll().size();

        // Create the RessourceVOD with an existing ID
        ressourceVOD.setId(1L);
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(ressourceVOD);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRessourceVODMockMvc.perform(post("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RessourceVOD in the database
        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = ressourceVODRepository.findAll().size();
        // set the field null
        ressourceVOD.setLibelle(null);

        // Create the RessourceVOD, which fails.
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(ressourceVOD);

        restRessourceVODMockMvc.perform(post("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isBadRequest());

        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLienRessourceVODIsRequired() throws Exception {
        int databaseSizeBeforeTest = ressourceVODRepository.findAll().size();
        // set the field null
        ressourceVOD.setLienRessourceVOD(null);

        // Create the RessourceVOD, which fails.
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(ressourceVOD);

        restRessourceVODMockMvc.perform(post("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isBadRequest());

        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRessourceVODS() throws Exception {
        // Initialize the database
        ressourceVODRepository.saveAndFlush(ressourceVOD);

        // Get all the ressourceVODList
        restRessourceVODMockMvc.perform(get("/api/ressource-vods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ressourceVOD.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lienRessourceVOD").value(hasItem(DEFAULT_LIEN_RESSOURCE_VOD)))
            .andExpect(jsonPath("$.[*].pathLogo").value(hasItem(DEFAULT_PATH_LOGO)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(DEFAULT_RESUME)));
    }
    
    @Test
    @Transactional
    public void getRessourceVOD() throws Exception {
        // Initialize the database
        ressourceVODRepository.saveAndFlush(ressourceVOD);

        // Get the ressourceVOD
        restRessourceVODMockMvc.perform(get("/api/ressource-vods/{id}", ressourceVOD.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ressourceVOD.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lienRessourceVOD").value(DEFAULT_LIEN_RESSOURCE_VOD))
            .andExpect(jsonPath("$.pathLogo").value(DEFAULT_PATH_LOGO))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.resume").value(DEFAULT_RESUME));
    }

    @Test
    @Transactional
    public void getNonExistingRessourceVOD() throws Exception {
        // Get the ressourceVOD
        restRessourceVODMockMvc.perform(get("/api/ressource-vods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRessourceVOD() throws Exception {
        // Initialize the database
        ressourceVODRepository.saveAndFlush(ressourceVOD);

        int databaseSizeBeforeUpdate = ressourceVODRepository.findAll().size();

        // Update the ressourceVOD
        RessourceVOD updatedRessourceVOD = ressourceVODRepository.findById(ressourceVOD.getId()).get();
        // Disconnect from session so that the updates on updatedRessourceVOD are not directly saved in db
        em.detach(updatedRessourceVOD);
        updatedRessourceVOD
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .lienRessourceVOD(UPDATED_LIEN_RESSOURCE_VOD)
            .pathLogo(UPDATED_PATH_LOGO)
            .code(UPDATED_CODE)
            .resume(UPDATED_RESUME);
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(updatedRessourceVOD);

        restRessourceVODMockMvc.perform(put("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isOk());

        // Validate the RessourceVOD in the database
        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeUpdate);
        RessourceVOD testRessourceVOD = ressourceVODList.get(ressourceVODList.size() - 1);
        assertThat(testRessourceVOD.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testRessourceVOD.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRessourceVOD.getLienRessourceVOD()).isEqualTo(UPDATED_LIEN_RESSOURCE_VOD);
        assertThat(testRessourceVOD.getPathLogo()).isEqualTo(UPDATED_PATH_LOGO);
        assertThat(testRessourceVOD.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRessourceVOD.getResume()).isEqualTo(UPDATED_RESUME);
    }

    @Test
    @Transactional
    public void updateNonExistingRessourceVOD() throws Exception {
        int databaseSizeBeforeUpdate = ressourceVODRepository.findAll().size();

        // Create the RessourceVOD
        RessourceVODDTO ressourceVODDTO = ressourceVODMapper.toDto(ressourceVOD);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRessourceVODMockMvc.perform(put("/api/ressource-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceVODDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RessourceVOD in the database
        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRessourceVOD() throws Exception {
        // Initialize the database
        ressourceVODRepository.saveAndFlush(ressourceVOD);

        int databaseSizeBeforeDelete = ressourceVODRepository.findAll().size();

        // Delete the ressourceVOD
        restRessourceVODMockMvc.perform(delete("/api/ressource-vods/{id}", ressourceVOD.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RessourceVOD> ressourceVODList = ressourceVODRepository.findAll();
        assertThat(ressourceVODList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
