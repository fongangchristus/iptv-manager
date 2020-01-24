package com.itgstore.iptv.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.itgstore.iptv.domain.enumeration.StatutAbonnement;

/**
 * A DTO for the {@link com.itgstore.iptv.domain.Abonnement} entity.
 */
public class AbonnementDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    private String lienRessourceVOD;

    private String lienRessourceTv;

    private StatutAbonnement statut;


    private Long packagesId;

    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getLienRessourceVOD() {
        return lienRessourceVOD;
    }

    public void setLienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
    }

    public String getLienRessourceTv() {
        return lienRessourceTv;
    }

    public void setLienRessourceTv(String lienRessourceTv) {
        this.lienRessourceTv = lienRessourceTv;
    }

    public StatutAbonnement getStatut() {
        return statut;
    }

    public void setStatut(StatutAbonnement statut) {
        this.statut = statut;
    }

    public Long getPackagesId() {
        return packagesId;
    }

    public void setPackagesId(Long packagesId) {
        this.packagesId = packagesId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbonnementDTO abonnementDTO = (AbonnementDTO) o;
        if (abonnementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abonnementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbonnementDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", lienRessourceVOD='" + getLienRessourceVOD() + "'" +
            ", lienRessourceTv='" + getLienRessourceTv() + "'" +
            ", statut='" + getStatut() + "'" +
            ", packagesId=" + getPackagesId() +
            ", clientId=" + getClientId() +
            "}";
    }
}
