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
 * A Chaine.
 */
@Entity
@Table(name = "chaine")
public class Chaine implements Serializable {

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
    @Column(name = "lien", nullable = false)
    private String lien;

    @Column(name = "code")
    private UUID code;

    @Column(name = "path_logo")
    private String pathLogo;

    @Column(name = "resume")
    private String resume;

    @ManyToOne
    @JsonIgnoreProperties("chaines")
    private CategorieTV categorieT;

    @ManyToMany(mappedBy = "packagechaines")
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

    public Chaine libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Chaine description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLien() {
        return lien;
    }

    public Chaine lien(String lien) {
        this.lien = lien;
        return this;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public UUID getCode() {
        return code;
    }

    public Chaine code(UUID code) {
        this.code = code;
        return this;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public Chaine pathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
        return this;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getResume() {
        return resume;
    }

    public Chaine resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public CategorieTV getCategorieT() {
        return categorieT;
    }

    public Chaine categorieT(CategorieTV categorieTV) {
        this.categorieT = categorieTV;
        return this;
    }

    public void setCategorieT(CategorieTV categorieTV) {
        this.categorieT = categorieTV;
    }

    public Set<Packages> getPackages() {
        return packages;
    }

    public Chaine packages(Set<Packages> packages) {
        this.packages = packages;
        return this;
    }

    public Chaine addPackages(Packages packages) {
        this.packages.add(packages);
        packages.getPackagechaines().add(this);
        return this;
    }

    public Chaine removePackages(Packages packages) {
        this.packages.remove(packages);
        packages.getPackagechaines().remove(this);
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
        if (!(o instanceof Chaine)) {
            return false;
        }
        return id != null && id.equals(((Chaine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Chaine{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", lien='" + getLien() + "'" +
            ", code='" + getCode() + "'" +
            ", pathLogo='" + getPathLogo() + "'" +
            ", resume='" + getResume() + "'" +
            "}";
    }
}
