package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.Packages;
import com.itgstore.iptv.repository.PackagesRepository;
import com.itgstore.iptv.service.PackagesService;
import com.itgstore.iptv.service.dto.PackagesDTO;
import com.itgstore.iptv.service.mapper.PackagesMapper;
import com.itgstore.iptv.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.itgstore.iptv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PackagesResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class PackagesResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIXT_UNITAIRE = 1D;
    private static final Double UPDATED_PRIXT_UNITAIRE = 2D;

    private static final String DEFAULT_PATH_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_PATH_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME = "AAAAAAAAAA";
    private static final String UPDATED_RESUME = "BBBBBBBBBB";

    @Autowired
    private PackagesRepository packagesRepository;

    @Mock
    private PackagesRepository packagesRepositoryMock;

    @Autowired
    private PackagesMapper packagesMapper;

    @Mock
    private PackagesService packagesServiceMock;

    @Autowired
    private PackagesService packagesService;

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

    private MockMvc restPackagesMockMvc;

    private Packages packages;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PackagesResource packagesResource = new PackagesResource(packagesService);
        this.restPackagesMockMvc = MockMvcBuilders.standaloneSetup(packagesResource)
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
    public static Packages createEntity(EntityManager em) {
        Packages packages = new Packages()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .prixtUnitaire(DEFAULT_PRIXT_UNITAIRE)
            .pathLogo(DEFAULT_PATH_LOGO)
            .resume(DEFAULT_RESUME);
        return packages;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Packages createUpdatedEntity(EntityManager em) {
        Packages packages = new Packages()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .prixtUnitaire(UPDATED_PRIXT_UNITAIRE)
            .pathLogo(UPDATED_PATH_LOGO)
            .resume(UPDATED_RESUME);
        return packages;
    }

    @BeforeEach
    public void initTest() {
        packages = createEntity(em);
    }

    @Test
    @Transactional
    public void createPackages() throws Exception {
        int databaseSizeBeforeCreate = packagesRepository.findAll().size();

        // Create the Packages
        PackagesDTO packagesDTO = packagesMapper.toDto(packages);
        restPackagesMockMvc.perform(post("/api/packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packagesDTO)))
            .andExpect(status().isCreated());

        // Validate the Packages in the database
        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeCreate + 1);
        Packages testPackages = packagesList.get(packagesList.size() - 1);
        assertThat(testPackages.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPackages.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPackages.getPrixtUnitaire()).isEqualTo(DEFAULT_PRIXT_UNITAIRE);
        assertThat(testPackages.getPathLogo()).isEqualTo(DEFAULT_PATH_LOGO);
        assertThat(testPackages.getResume()).isEqualTo(DEFAULT_RESUME);
    }

    @Test
    @Transactional
    public void createPackagesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = packagesRepository.findAll().size();

        // Create the Packages with an existing ID
        packages.setId(1L);
        PackagesDTO packagesDTO = packagesMapper.toDto(packages);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPackagesMockMvc.perform(post("/api/packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Packages in the database
        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = packagesRepository.findAll().size();
        // set the field null
        packages.setLibelle(null);

        // Create the Packages, which fails.
        PackagesDTO packagesDTO = packagesMapper.toDto(packages);

        restPackagesMockMvc.perform(post("/api/packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packagesDTO)))
            .andExpect(status().isBadRequest());

        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPackages() throws Exception {
        // Initialize the database
        packagesRepository.saveAndFlush(packages);

        // Get all the packagesList
        restPackagesMockMvc.perform(get("/api/packages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(packages.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].prixtUnitaire").value(hasItem(DEFAULT_PRIXT_UNITAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].pathLogo").value(hasItem(DEFAULT_PATH_LOGO)))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(DEFAULT_RESUME)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPackagesWithEagerRelationshipsIsEnabled() throws Exception {
        PackagesResource packagesResource = new PackagesResource(packagesServiceMock);
        when(packagesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restPackagesMockMvc = MockMvcBuilders.standaloneSetup(packagesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPackagesMockMvc.perform(get("/api/packages?eagerload=true"))
        .andExpect(status().isOk());

        verify(packagesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPackagesWithEagerRelationshipsIsNotEnabled() throws Exception {
        PackagesResource packagesResource = new PackagesResource(packagesServiceMock);
            when(packagesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restPackagesMockMvc = MockMvcBuilders.standaloneSetup(packagesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPackagesMockMvc.perform(get("/api/packages?eagerload=true"))
        .andExpect(status().isOk());

            verify(packagesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPackages() throws Exception {
        // Initialize the database
        packagesRepository.saveAndFlush(packages);

        // Get the packages
        restPackagesMockMvc.perform(get("/api/packages/{id}", packages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(packages.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.prixtUnitaire").value(DEFAULT_PRIXT_UNITAIRE.doubleValue()))
            .andExpect(jsonPath("$.pathLogo").value(DEFAULT_PATH_LOGO))
            .andExpect(jsonPath("$.resume").value(DEFAULT_RESUME));
    }

    @Test
    @Transactional
    public void getNonExistingPackages() throws Exception {
        // Get the packages
        restPackagesMockMvc.perform(get("/api/packages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePackages() throws Exception {
        // Initialize the database
        packagesRepository.saveAndFlush(packages);

        int databaseSizeBeforeUpdate = packagesRepository.findAll().size();

        // Update the packages
        Packages updatedPackages = packagesRepository.findById(packages.getId()).get();
        // Disconnect from session so that the updates on updatedPackages are not directly saved in db
        em.detach(updatedPackages);
        updatedPackages
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .prixtUnitaire(UPDATED_PRIXT_UNITAIRE)
            .pathLogo(UPDATED_PATH_LOGO)
            .resume(UPDATED_RESUME);
        PackagesDTO packagesDTO = packagesMapper.toDto(updatedPackages);

        restPackagesMockMvc.perform(put("/api/packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packagesDTO)))
            .andExpect(status().isOk());

        // Validate the Packages in the database
        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeUpdate);
        Packages testPackages = packagesList.get(packagesList.size() - 1);
        assertThat(testPackages.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPackages.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPackages.getPrixtUnitaire()).isEqualTo(UPDATED_PRIXT_UNITAIRE);
        assertThat(testPackages.getPathLogo()).isEqualTo(UPDATED_PATH_LOGO);
        assertThat(testPackages.getResume()).isEqualTo(UPDATED_RESUME);
    }

    @Test
    @Transactional
    public void updateNonExistingPackages() throws Exception {
        int databaseSizeBeforeUpdate = packagesRepository.findAll().size();

        // Create the Packages
        PackagesDTO packagesDTO = packagesMapper.toDto(packages);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPackagesMockMvc.perform(put("/api/packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Packages in the database
        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePackages() throws Exception {
        // Initialize the database
        packagesRepository.saveAndFlush(packages);

        int databaseSizeBeforeDelete = packagesRepository.findAll().size();

        // Delete the packages
        restPackagesMockMvc.perform(delete("/api/packages/{id}", packages.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Packages> packagesList = packagesRepository.findAll();
        assertThat(packagesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
