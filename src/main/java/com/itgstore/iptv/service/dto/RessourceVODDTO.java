package com.itgstore.iptv.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.itgstore.iptv.domain.RessourceVOD} entity.
 */
public class RessourceVODDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    @NotNull
    private String lienRessourceVOD;

    private String pathLogo;

    private UUID code;

    private String resume;


    private Long categorieVODId;

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

    public String getLienRessourceVOD() {
        return lienRessourceVOD;
    }

    public void setLienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Long getCategorieVODId() {
        return categorieVODId;
    }

    public void setCategorieVODId(Long categorieVODId) {
        this.categorieVODId = categorieVODId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RessourceVODDTO ressourceVODDTO = (RessourceVODDTO) o;
        if (ressourceVODDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ressourceVODDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RessourceVODDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", lienRessourceVOD='" + getLienRessourceVOD() + "'" +
            ", pathLogo='" + getPathLogo() + "'" +
            ", code='" + getCode() + "'" +
            ", resume='" + getResume() + "'" +
            ", categorieVODId=" + getCategorieVODId() +
            "}";
    }
}
