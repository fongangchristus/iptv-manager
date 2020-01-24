package com.itgstore.iptv.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Packages.
 */
@Entity
@Table(name = "packages")
public class Packages implements Serializable {

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

    @Column(name = "prixt_unitaire")
    private Double prixtUnitaire;

    @Column(name = "path_logo")
    private String pathLogo;

    @Column(name = "resume")
    private String resume;

    @ManyToMany
    @JoinTable(name = "packages_packagechaine",
               joinColumns = @JoinColumn(name = "packages_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "packagechaine_id", referencedColumnName = "id"))
    private Set<Chaine> packagechaines = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "packages_packagevod",
               joinColumns = @JoinColumn(name = "packages_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "packagevod_id", referencedColumnName = "id"))
    private Set<RessourceVOD> packageVODS = new HashSet<>();

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

    public Packages libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Packages description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixtUnitaire() {
        return prixtUnitaire;
    }

    public Packages prixtUnitaire(Double prixtUnitaire) {
        this.prixtUnitaire = prixtUnitaire;
        return this;
    }

    public void setPrixtUnitaire(Double prixtUnitaire) {
        this.prixtUnitaire = prixtUnitaire;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public Packages pathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
        return this;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getResume() {
        return resume;
    }

    public Packages resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Set<Chaine> getPackagechaines() {
        return packagechaines;
    }

    public Packages packagechaines(Set<Chaine> chaines) {
        this.packagechaines = chaines;
        return this;
    }

    public Packages addPackagechaine(Chaine chaine) {
        this.packagechaines.add(chaine);
        chaine.getPackages().add(this);
        return this;
    }

    public Packages removePackagechaine(Chaine chaine) {
        this.packagechaines.remove(chaine);
        chaine.getPackages().remove(this);
        return this;
    }

    public void setPackagechaines(Set<Chaine> chaines) {
        this.packagechaines = chaines;
    }

    public Set<RessourceVOD> getPackageVODS() {
        return packageVODS;
    }

    public Packages packageVODS(Set<RessourceVOD> ressourceVODS) {
        this.packageVODS = ressourceVODS;
        return this;
    }

    public Packages addPackageVOD(RessourceVOD ressourceVOD) {
        this.packageVODS.add(ressourceVOD);
        ressourceVOD.getPackages().add(this);
        return this;
    }

    public Packages removePackageVOD(RessourceVOD ressourceVOD) {
        this.packageVODS.remove(ressourceVOD);
        ressourceVOD.getPackages().remove(this);
        return this;
    }

    public void setPackageVODS(Set<RessourceVOD> ressourceVODS) {
        this.packageVODS = ressourceVODS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Packages)) {
            return false;
        }
        return id != null && id.equals(((Packages) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Packages{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixtUnitaire=" + getPrixtUnitaire() +
            ", pathLogo='" + getPathLogo() + "'" +
            ", resume='" + getResume() + "'" +
            "}";
    }
}
