package com.itgstore.iptv.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A RessourceVOD.
 */
@Entity
@Table(name = "ressource_vod")
public class RessourceVOD implements Serializable {

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
    @Column(name = "lien_ressource_vod", nullable = false)
    private String lienRessourceVOD;

    @Column(name = "path_logo")
    private String pathLogo;

    @Column(name = "code")
    private UUID code;

    @Column(name = "resume")
    private String resume;

    @ManyToOne
    @JsonIgnoreProperties("ressourceVODS")
    private CategorieVOD categorieVOD;

    @ManyToMany(mappedBy = "packageVODS")
    @JsonIgnore
    private Set<Packages> packages = new HashSet<>();

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

    public RessourceVOD libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public RessourceVOD description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLienRessourceVOD() {
        return lienRessourceVOD;
    }

    public RessourceVOD lienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
        return this;
    }

    public void setLienRessourceVOD(String lienRessourceVOD) {
        this.lienRessourceVOD = lienRessourceVOD;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public RessourceVOD pathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
        return this;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public UUID getCode() {
        return code;
    }

    public RessourceVOD code(UUID code) {
        this.code = code;
        return this;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getResume() {
        return resume;
    }

    public RessourceVOD resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public CategorieVOD getCategorieVOD() {
        return categorieVOD;
    }

    public RessourceVOD categorieVOD(CategorieVOD categorieVOD) {
        this.categorieVOD = categorieVOD;
        return this;
    }

    public void setCategorieVOD(CategorieVOD categorieVOD) {
        this.categorieVOD = categorieVOD;
    }

    public Set<Packages> getPackages() {
        return packages;
    }

    public RessourceVOD packages(Set<Packages> packages) {
        this.packages = packages;
        return this;
    }

    public RessourceVOD addPackages(Packages packages) {
        this.packages.add(packages);
        packages.getPackageVODS().add(this);
        return this;
    }

    public RessourceVOD removePackages(Packages packages) {
        this.packages.remove(packages);
        packages.getPackageVODS().remove(this);
        return this;
    }

    public void setPackages(Set<Packages> packages) {
        this.packages = packages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RessourceVOD)) {
            return false;
        }
        return id != null && id.equals(((RessourceVOD) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RessourceVOD{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", lienRessourceVOD='" + getLienRessourceVOD() + "'" +
            ", pathLogo='" + getPathLogo() + "'" +
            ", code='" + getCode() + "'" +
            ", resume='" + getResume() + "'" +
            "}";
    }
}
