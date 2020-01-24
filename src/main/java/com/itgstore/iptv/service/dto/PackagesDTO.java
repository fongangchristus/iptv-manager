package com.itgstore.iptv.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.itgstore.iptv.domain.Packages} entity.
 */
public class PackagesDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    private Double prixtUnitaire;

    private String pathLogo;

    private String resume;


    private Set<ChaineDTO> packagechaines = new HashSet<>();

    private Set<RessourceVODDTO> packageVODS = new HashSet<>();

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

    public Double getPrixtUnitaire() {
        return prixtUnitaire;
    }

    public void setPrixtUnitaire(Double prixtUnitaire) {
        this.prixtUnitaire = prixtUnitaire;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Set<ChaineDTO> getPackagechaines() {
        return packagechaines;
    }

    public void setPackagechaines(Set<ChaineDTO> chaines) {
        this.packagechaines = chaines;
    }

    public Set<RessourceVODDTO> getPackageVODS() {
        return packageVODS;
    }

    public void setPackageVODS(Set<RessourceVODDTO> ressourceVODS) {
        this.packageVODS = ressourceVODS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PackagesDTO packagesDTO = (PackagesDTO) o;
        if (packagesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), packagesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PackagesDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixtUnitaire=" + getPrixtUnitaire() +
            ", pathLogo='" + getPathLogo() + "'" +
            ", resume='" + getResume() + "'" +
            "}";
    }
}
