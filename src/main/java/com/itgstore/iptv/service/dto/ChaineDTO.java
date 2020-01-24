package com.itgstore.iptv.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.itgstore.iptv.domain.Chaine} entity.
 */
public class ChaineDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    @NotNull
    private String lien;

    private UUID code;

    private String pathLogo;

    private String resume;


    private Long categorieTId;

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

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
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

    public Long getCategorieTId() {
        return categorieTId;
    }

    public void setCategorieTId(Long categorieTVId) {
        this.categorieTId = categorieTVId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChaineDTO chaineDTO = (ChaineDTO) o;
        if (chaineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chaineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChaineDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", lien='" + getLien() + "'" +
            ", code='" + getCode() + "'" +
            ", pathLogo='" + getPathLogo() + "'" +
            ", resume='" + getResume() + "'" +
            ", categorieTId=" + getCategorieTId() +
            "}";
    }
}
