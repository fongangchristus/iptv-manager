package com.itgstore.iptv.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.itgstore.iptv.domain.enumeration.StatutAbonnement;

/**
 * A Abonnement.
 */
@Entity
@Table(name = "abonnement")
public class Abonnement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @NotNull
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "lien_ressource_vod")
    private String lienRessourceVOD;

    @Column(name = "lien_ressource_tv")
    private String lienRessourceTv;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutAbonnement statut;

    @ManyToOne
    @JsonIgnoreProperties("abonnements")
    private Packages packages;

    @ManyToOne
    @JsonIgnoreProperties("abonnements")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Abonnement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Abonnement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Abonnement dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Abonnement dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getLienRessourceVOD() {
        return lienRessourceVOD;
    }

    public Abonnement lienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
        return this;
    }

    public void setLienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
    }

    public String getLienRessourceTv() {
        return lienRessourceTv;
    }

    public Abonnement lienRessourceTv(String lienRessourceTv) {
        this.lienRessourceTv = lienRessourceTv;
        return this;
    }

    public void setLienRessourceTv(String lienRessourceTv) {
        this.lienRessourceTv = lienRessourceTv;
    }

    public StatutAbonnement getStatut() {
        return statut;
    }

    public Abonnement statut(StatutAbonnement statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutAbonnement statut) {
        this.statut = statut;
    }

    public Packages getPackages() {
        return packages;
    }

    public Abonnement packages(Packages packages) {
        this.packages = packages;
        return this;
    }

    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    public Client getClient() {
        return client;
    }

    public Abonnement client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Abonnement)) {
            return false;
        }
        return id != null && id.equals(((Abonnement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", lienRessourceVOD='" + getLienRessourceVOD() + "'" +
            ", lienRessourceTv='" + getLienRessourceTv() + "'" +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
