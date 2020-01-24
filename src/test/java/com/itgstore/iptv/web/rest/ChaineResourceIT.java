package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.Chaine;
import com.itgstore.iptv.repository.ChaineRepository;
import com.itgstore.iptv.service.ChaineService;
import com.itgstore.iptv.service.dto.ChaineDTO;
import com.itgstore.iptv.service.mapper.ChaineMapper;
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
 * Integration tests for the {@link ChaineResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class ChaineResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN = "AAAAAAAAAA";
    private static final String UPDATED_LIEN = "BBBBBBBBBB";

    private static final UUID DEFAULT_CODE = UUID.randomUUID();
    private static final UUID UPDATED_CODE = UUID.randomUUID();

    private static final String DEFAULT_PATH_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_PATH_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME = "AAAAAAAAAA";
    private static final String UPDATED_RESUME = "BBBBBBBBBB";

    @Autowired
    private ChaineRepository chaineRepository;

    @Autowired
    private ChaineMapper chaineMapper;

    @Autowired
    private ChaineService chaineService;

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

    private MockMvc restChaineMockMvc;

    private Chaine chaine;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChaineResource chaineResource = new ChaineResource(chaineService);
        this.restChaineMockMvc = MockMvcBuilders.standaloneSetup(chaineResource)
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
    public static Chaine createEntity(EntityManager em) {
        Chaine chaine = new Chaine()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .lien(DEFAULT_LIEN)
            .code(DEFAULT_CODE)
            .pathLogo(DEFAULT_PATH_LOGO)
            .resume(DEFAULT_RESUME);
        return chaine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chaine createUpdatedEntity(EntityManager em) {
        Chaine chaine = new Chaine()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .lien(UPDATED_LIEN)
            .code(UPDATED_CODE)
            .pathLogo(UPDATED_PATH_LOGO)
            .resume(UPDATED_RESUME);
        return chaine;
    }

    @BeforeEach
    public void initTest() {
        chaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createChaine() throws Exception {
        int databaseSizeBeforeCreate = chaineRepository.findAll().size();

        // Create the Chaine
        ChaineDTO chaineDTO = chaineMapper.toDto(chaine);
        restChaineMockMvc.perform(post("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isCreated());

        // Validate the Chaine in the database
        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeCreate + 1);
        Chaine testChaine = chaineList.get(chaineList.size() - 1);
        assertThat(testChaine.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testChaine.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testChaine.getLien()).isEqualTo(DEFAULT_LIEN);
        assertThat(testChaine.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testChaine.getPathLogo()).isEqualTo(DEFAULT_PATH_LOGO);
        assertThat(testChaine.getResume()).isEqualTo(DEFAULT_RESUME);
    }

    @Test
    @Transactional
    public void createChaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chaineRepository.findAll().size();

        // Create the Chaine with an existing ID
        chaine.setId(1L);
        ChaineDTO chaineDTO = chaineMapper.toDto(chaine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChaineMockMvc.perform(post("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chaine in the database
        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = chaineRepository.findAll().size();
        // set the field null
        chaine.setLibelle(null);

        // Create the Chaine, which fails.
        ChaineDTO chaineDTO = chaineMapper.toDto(chaine);

        restChaineMockMvc.perform(post("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isBadRequest());

        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLienIsRequired() throws Exception {
        int databaseSizeBeforeTest = chaineRepository.findAll().size();
        // set the field null
        chaine.setLien(null);

        // Create the Chaine, which fails.
        ChaineDTO chaineDTO = chaineMapper.toDto(chaine);

        restChaineMockMvc.perform(post("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isBadRequest());

        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChaines() throws Exception {
        // Initialize the database
        chaineRepository.saveAndFlush(chaine);

        // Get all the chaineList
        restChaineMockMvc.perform(get("/api/chaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lien").value(hasItem(DEFAULT_LIEN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].pathLogo").value(hasItem(DEFAULT_PATH_LOGO)))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(DEFAULT_RESUME)));
    }
    
    @Test
    @Transactional
    public void getChaine() throws Exception {
        // Initialize the database
        chaineRepository.saveAndFlush(chaine);

        // Get the chaine
        restChaineMockMvc.perform(get("/api/chaines/{id}", chaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chaine.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lien").value(DEFAULT_LIEN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.pathLogo").value(DEFAULT_PATH_LOGO))
            .andExpect(jsonPath("$.resume").value(DEFAULT_RESUME));
    }

    @Test
    @Transactional
    public void getNonExistingChaine() throws Exception {
        // Get the chaine
        restChaineMockMvc.perform(get("/api/chaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChaine() throws Exception {
        // Initialize the database
        chaineRepository.saveAndFlush(chaine);

        int databaseSizeBeforeUpdate = chaineRepository.findAll().size();

        // Update the chaine
        Chaine updatedChaine = chaineRepository.findById(chaine.getId()).get();
        // Disconnect from session so that the updates on updatedChaine are not directly saved in db
        em.detach(updatedChaine);
        updatedChaine
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .lien(UPDATED_LIEN)
            .code(UPDATED_CODE)
            .pathLogo(UPDATED_PATH_LOGO)
            .resume(UPDATED_RESUME);
        ChaineDTO chaineDTO = chaineMapper.toDto(updatedChaine);

        restChaineMockMvc.perform(put("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isOk());

        // Validate the Chaine in the database
        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeUpdate);
        Chaine testChaine = chaineList.get(chaineList.size() - 1);
        assertThat(testChaine.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testChaine.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testChaine.getLien()).isEqualTo(UPDATED_LIEN);
        assertThat(testChaine.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testChaine.getPathLogo()).isEqualTo(UPDATED_PATH_LOGO);
        assertThat(testChaine.getResume()).isEqualTo(UPDATED_RESUME);
    }

    @Test
    @Transactional
    public void updateNonExistingChaine() throws Exception {
        int databaseSizeBeforeUpdate = chaineRepository.findAll().size();

        // Create the Chaine
        ChaineDTO chaineDTO = chaineMapper.toDto(chaine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChaineMockMvc.perform(put("/api/chaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chaine in the database
        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChaine() throws Exception {
        // Initialize the database
        chaineRepository.saveAndFlush(chaine);

        int databaseSizeBeforeDelete = chaineRepository.findAll().size();

        // Delete the chaine
        restChaineMockMvc.perform(delete("/api/chaines/{id}", chaine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Chaine> chaineList = chaineRepository.findAll();
        assertThat(chaineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
