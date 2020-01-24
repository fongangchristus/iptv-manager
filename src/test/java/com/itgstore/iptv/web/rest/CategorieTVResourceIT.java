package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.CategorieTV;
import com.itgstore.iptv.repository.CategorieTVRepository;
import com.itgstore.iptv.service.CategorieTVService;
import com.itgstore.iptv.service.dto.CategorieTVDTO;
import com.itgstore.iptv.service.mapper.CategorieTVMapper;
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
 * Integration tests for the {@link CategorieTVResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class CategorieTVResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CategorieTVRepository categorieTVRepository;

    @Autowired
    private CategorieTVMapper categorieTVMapper;

    @Autowired
    private CategorieTVService categorieTVService;

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

    private MockMvc restCategorieTVMockMvc;

    private CategorieTV categorieTV;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategorieTVResource categorieTVResource = new CategorieTVResource(categorieTVService);
        this.restCategorieTVMockMvc = MockMvcBuilders.standaloneSetup(categorieTVResource)
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
    public static CategorieTV createEntity(EntityManager em) {
        CategorieTV categorieTV = new CategorieTV()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION);
        return categorieTV;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieTV createUpdatedEntity(EntityManager em) {
        CategorieTV categorieTV = new CategorieTV()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION);
        return categorieTV;
    }

    @BeforeEach
    public void initTest() {
        categorieTV = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieTV() throws Exception {
        int databaseSizeBeforeCreate = categorieTVRepository.findAll().size();

        // Create the CategorieTV
        CategorieTVDTO categorieTVDTO = categorieTVMapper.toDto(categorieTV);
        restCategorieTVMockMvc.perform(post("/api/categorie-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieTVDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieTV in the database
        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieTV testCategorieTV = categorieTVList.get(categorieTVList.size() - 1);
        assertThat(testCategorieTV.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testCategorieTV.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCategorieTVWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieTVRepository.findAll().size();

        // Create the CategorieTV with an existing ID
        categorieTV.setId(1L);
        CategorieTVDTO categorieTVDTO = categorieTVMapper.toDto(categorieTV);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieTVMockMvc.perform(post("/api/categorie-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieTVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieTV in the database
        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieTVRepository.findAll().size();
        // set the field null
        categorieTV.setLibele(null);

        // Create the CategorieTV, which fails.
        CategorieTVDTO categorieTVDTO = categorieTVMapper.toDto(categorieTV);

        restCategorieTVMockMvc.perform(post("/api/categorie-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieTVDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorieTVS() throws Exception {
        // Initialize the database
        categorieTVRepository.saveAndFlush(categorieTV);

        // Get all the categorieTVList
        restCategorieTVMockMvc.perform(get("/api/categorie-tvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieTV.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getCategorieTV() throws Exception {
        // Initialize the database
        categorieTVRepository.saveAndFlush(categorieTV);

        // Get the categorieTV
        restCategorieTVMockMvc.perform(get("/api/categorie-tvs/{id}", categorieTV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categorieTV.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingCategorieTV() throws Exception {
        // Get the categorieTV
        restCategorieTVMockMvc.perform(get("/api/categorie-tvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieTV() throws Exception {
        // Initialize the database
        categorieTVRepository.saveAndFlush(categorieTV);

        int databaseSizeBeforeUpdate = categorieTVRepository.findAll().size();

        // Update the categorieTV
        CategorieTV updatedCategorieTV = categorieTVRepository.findById(categorieTV.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieTV are not directly saved in db
        em.detach(updatedCategorieTV);
        updatedCategorieTV
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION);
        CategorieTVDTO categorieTVDTO = categorieTVMapper.toDto(updatedCategorieTV);

        restCategorieTVMockMvc.perform(put("/api/categorie-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieTVDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieTV in the database
        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeUpdate);
        CategorieTV testCategorieTV = categorieTVList.get(categorieTVList.size() - 1);
        assertThat(testCategorieTV.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testCategorieTV.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieTV() throws Exception {
        int databaseSizeBeforeUpdate = categorieTVRepository.findAll().size();

        // Create the CategorieTV
        CategorieTVDTO categorieTVDTO = categorieTVMapper.toDto(categorieTV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieTVMockMvc.perform(put("/api/categorie-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categorieTVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieTV in the database
        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieTV() throws Exception {
        // Initialize the database
        categorieTVRepository.saveAndFlush(categorieTV);

        int databaseSizeBeforeDelete = categorieTVRepository.findAll().size();

        // Delete the categorieTV
        restCategorieTVMockMvc.perform(delete("/api/categorie-tvs/{id}", categorieTV.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieTV> categorieTVList = categorieTVRepository.findAll();
        assertThat(categorieTVList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
