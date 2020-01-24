package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.CategorieVOD;
import com.itgstore.iptv.repository.CategorieVODRepository;
import com.itgstore.iptv.service.CategorieVODService;
import com.itgstore.iptv.service.dto.CategorieVODDTO;
import com.itgstore.iptv.service.mapper.CategorieVODMapper;
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

import static com.itgstore.iptv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CategorieVODResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class CategorieVODResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CategorieVODRepository categorieVODRepository;

    @Autowired
    private CategorieVODMapper categorieVODMapper;

    @Autowired
    private CategorieVODService categorieVODService;

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

    private MockMvc restCategorieVODMockMvc;

    private CategorieVOD categorieVOD;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategorieVODResource categorieVODResource = new CategorieVODResource(categorieVODService);
        this.restCategorieVODMockMvc = MockMvcBuilders.standaloneSetup(categorieVODResource)
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
    public static CategorieVOD createEntity(EntityManager em) {
        CategorieVOD categorieVOD = new CategorieVOD()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION);
        return categorieVOD;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieVOD createUpdatedEntity(EntityManager em) {
        CategorieVOD categorieVOD = new CategorieVOD()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION);
        return categorieVOD;
    }

    @BeforeEach
    public void initTest() {
        categorieVOD = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieVOD() throws Exception {
        int databaseSizeBeforeCreate = categorieVODRepository.findAll().size();

        // Create the CategorieVOD
        CategorieVODDTO categorieVODDTO = categorieVODMapper.toDto(categorieVOD);
        restCategorieVODMockMvc.perform(post("/api/categorie-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieVODDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieVOD in the database
        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieVOD testCategorieVOD = categorieVODList.get(categorieVODList.size() - 1);
        assertThat(testCategorieVOD.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testCategorieVOD.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCategorieVODWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieVODRepository.findAll().size();

        // Create the CategorieVOD with an existing ID
        categorieVOD.setId(1L);
        CategorieVODDTO categorieVODDTO = categorieVODMapper.toDto(categorieVOD);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieVODMockMvc.perform(post("/api/categorie-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieVODDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieVOD in the database
        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieVODRepository.findAll().size();
        // set the field null
        categorieVOD.setLibele(null);

        // Create the CategorieVOD, which fails.
        CategorieVODDTO categorieVODDTO = categorieVODMapper.toDto(categorieVOD);

        restCategorieVODMockMvc.perform(post("/api/categorie-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieVODDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorieVODS() throws Exception {
        // Initialize the database
        categorieVODRepository.saveAndFlush(categorieVOD);

        // Get all the categorieVODList
        restCategorieVODMockMvc.perform(get("/api/categorie-vods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieVOD.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getCategorieVOD() throws Exception {
        // Initialize the database
        categorieVODRepository.saveAndFlush(categorieVOD);

        // Get the categorieVOD
        restCategorieVODMockMvc.perform(get("/api/categorie-vods/{id}", categorieVOD.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categorieVOD.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingCategorieVOD() throws Exception {
        // Get the categorieVOD
        restCategorieVODMockMvc.perform(get("/api/categorie-vods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieVOD() throws Exception {
        // Initialize the database
        categorieVODRepository.saveAndFlush(categorieVOD);

        int databaseSizeBeforeUpdate = categorieVODRepository.findAll().size();

        // Update the categorieVOD
        CategorieVOD updatedCategorieVOD = categorieVODRepository.findById(categorieVOD.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieVOD are not directly saved in db
        em.detach(updatedCategorieVOD);
        updatedCategorieVOD
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION);
        CategorieVODDTO categorieVODDTO = categorieVODMapper.toDto(updatedCategorieVOD);

        restCategorieVODMockMvc.perform(put("/api/categorie-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieVODDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieVOD in the database
        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeUpdate);
        CategorieVOD testCategorieVOD = categorieVODList.get(categorieVODList.size() - 1);
        assertThat(testCategorieVOD.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testCategorieVOD.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieVOD() throws Exception {
        int databaseSizeBeforeUpdate = categorieVODRepository.findAll().size();

        // Create the CategorieVOD
        CategorieVODDTO categorieVODDTO = categorieVODMapper.toDto(categorieVOD);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieVODMockMvc.perform(put("/api/categorie-vods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieVODDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieVOD in the database
        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieVOD() throws Exception {
        // Initialize the database
        categorieVODRepository.saveAndFlush(categorieVOD);

        int databaseSizeBeforeDelete = categorieVODRepository.findAll().size();

        // Delete the categorieVOD
        restCategorieVODMockMvc.perform(delete("/api/categorie-vods/{id}", categorieVOD.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieVOD> categorieVODList = categorieVODRepository.findAll();
        assertThat(categorieVODList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
