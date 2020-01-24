package com.itgstore.iptv.web.rest;

import com.itgstore.iptv.IptvmanagerApp;
import com.itgstore.iptv.domain.Abonnement;
import com.itgstore.iptv.repository.AbonnementRepository;
import com.itgstore.iptv.service.AbonnementService;
import com.itgstore.iptv.service.dto.AbonnementDTO;
import com.itgstore.iptv.service.mapper.AbonnementMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.itgstore.iptv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.itgstore.iptv.domain.enumeration.StatutAbonnement;
/**
 * Integration tests for the {@link AbonnementResource} REST controller.
 */
@SpringBootTest(classes = IptvmanagerApp.class)
public class AbonnementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEN_RESSOURCE_VOD = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_RESSOURCE_VOD = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN_RESSOURCE_TV = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_RESSOURCE_TV = "BBBBBBBBBB";

    private static final StatutAbonnement DEFAULT_STATUT = StatutAbonnement.ACTIVE;
    private static final StatutAbonnement UPDATED_STATUT = StatutAbonnement.DESACTIVE;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private AbonnementMapper abonnementMapper;

    @Autowired
    private AbonnementService abonnementService;

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

    private MockMvc restAbonnementMockMvc;

    private Abonnement abonnement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AbonnementResource abonnementResource = new AbonnementResource(abonnementService);
        this.restAbonnementMockMvc = MockMvcBuilders.standaloneSetup(abonnementResource)
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
    public static Abonnement createEntity(EntityManager em) {
        Abonnement abonnement = new Abonnement()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .lienRessourceVOD(DEFAULT_LIEN_RESSOURCE_VOD)
            .lienRessourceTv(DEFAULT_LIEN_RESSOURCE_TV)
            .statut(DEFAULT_STATUT);
        return abonnement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Abonnement createUpdatedEntity(EntityManager em) {
        Abonnement abonnement = new Abonnement()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .lienRessourceVOD(UPDATED_LIEN_RESSOURCE_VOD)
            .lienRessourceTv(UPDATED_LIEN_RESSOURCE_TV)
            .statut(UPDATED_STATUT);
        return abonnement;
    }

    @BeforeEach
    public void initTest() {
        abonnement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbonnement() throws Exception {
        int databaseSizeBeforeCreate = abonnementRepository.findAll().size();

        // Create the Abonnement
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);
        restAbonnementMockMvc.perform(post("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isCreated());

        // Validate the Abonnement in the database
        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeCreate + 1);
        Abonnement testAbonnement = abonnementList.get(abonnementList.size() - 1);
        assertThat(testAbonnement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testAbonnement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAbonnement.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testAbonnement.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testAbonnement.getLienRessourceVOD()).isEqualTo(DEFAULT_LIEN_RESSOURCE_VOD);
        assertThat(testAbonnement.getLienRessourceTv()).isEqualTo(DEFAULT_LIEN_RESSOURCE_TV);
        assertThat(testAbonnement.getStatut()).isEqualTo(DEFAULT_STATUT);
    }

    @Test
    @Transactional
    public void createAbonnementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abonnementRepository.findAll().size();

        // Create the Abonnement with an existing ID
        abonnement.setId(1L);
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbonnementMockMvc.perform(post("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Abonnement in the database
        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = abonnementRepository.findAll().size();
        // set the field null
        abonnement.setLibelle(null);

        // Create the Abonnement, which fails.
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);

        restAbonnementMockMvc.perform(post("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isBadRequest());

        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateDebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = abonnementRepository.findAll().size();
        // set the field null
        abonnement.setDateDebut(null);

        // Create the Abonnement, which fails.
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);

        restAbonnementMockMvc.perform(post("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isBadRequest());

        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = abonnementRepository.findAll().size();
        // set the field null
        abonnement.setDateFin(null);

        // Create the Abonnement, which fails.
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);

        restAbonnementMockMvc.perform(post("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isBadRequest());

        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAbonnements() throws Exception {
        // Initialize the database
        abonnementRepository.saveAndFlush(abonnement);

        // Get all the abonnementList
        restAbonnementMockMvc.perform(get("/api/abonnements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abonnement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].lienRessourceVOD").value(hasItem(DEFAULT_LIEN_RESSOURCE_VOD)))
            .andExpect(jsonPath("$.[*].lienRessourceTv").value(hasItem(DEFAULT_LIEN_RESSOURCE_TV)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())));
    }
    
    @Test
    @Transactional
    public void getAbonnement() throws Exception {
        // Initialize the database
        abonnementRepository.saveAndFlush(abonnement);

        // Get the abonnement
        restAbonnementMockMvc.perform(get("/api/abonnements/{id}", abonnement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(abonnement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.lienRessourceVOD").value(DEFAULT_LIEN_RESSOURCE_VOD))
            .andExpect(jsonPath("$.lienRessourceTv").value(DEFAULT_LIEN_RESSOURCE_TV))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAbonnement() throws Exception {
        // Get the abonnement
        restAbonnementMockMvc.perform(get("/api/abonnements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbonnement() throws Exception {
        // Initialize the database
        abonnementRepository.saveAndFlush(abonnement);

        int databaseSizeBeforeUpdate = abonnementRepository.findAll().size();

        // Update the abonnement
        Abonnement updatedAbonnement = abonnementRepository.findById(abonnement.getId()).get();
        // Disconnect from session so that the updates on updatedAbonnement are not directly saved in db
        em.detach(updatedAbonnement);
        updatedAbonnement
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .lienRessourceVOD(UPDATED_LIEN_RESSOURCE_VOD)
            .lienRessourceTv(UPDATED_LIEN_RESSOURCE_TV)
            .statut(UPDATED_STATUT);
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(updatedAbonnement);

        restAbonnementMockMvc.perform(put("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isOk());

        // Validate the Abonnement in the database
        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeUpdate);
        Abonnement testAbonnement = abonnementList.get(abonnementList.size() - 1);
        assertThat(testAbonnement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testAbonnement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAbonnement.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testAbonnement.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testAbonnement.getLienRessourceVOD()).isEqualTo(UPDATED_LIEN_RESSOURCE_VOD);
        assertThat(testAbonnement.getLienRessourceTv()).isEqualTo(UPDATED_LIEN_RESSOURCE_TV);
        assertThat(testAbonnement.getStatut()).isEqualTo(UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingAbonnement() throws Exception {
        int databaseSizeBeforeUpdate = abonnementRepository.findAll().size();

        // Create the Abonnement
        AbonnementDTO abonnementDTO = abonnementMapper.toDto(abonnement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbonnementMockMvc.perform(put("/api/abonnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abonnementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Abonnement in the database
        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbonnement() throws Exception {
        // Initialize the database
        abonnementRepository.saveAndFlush(abonnement);

        int databaseSizeBeforeDelete = abonnementRepository.findAll().size();

        // Delete the abonnement
        restAbonnementMockMvc.perform(delete("/api/abonnements/{id}", abonnement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Abonnement> abonnementList = abonnementRepository.findAll();
        assertThat(abonnementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
