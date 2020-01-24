package com.itgstore.iptv.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.itgstore.iptv.domain.Adresse} entity.
 */
public class AdresseDTO implements Serializable {

    private Long id;

    private String pays;

    private String region;

    @NotNull
    private String ville;

    private String telephone1;

    private String telephone2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdresseDTO adresseDTO = (AdresseDTO) o;
        if (adresseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adresseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdresseDTO{" +
            "id=" + getId() +
            ", pays='" + getPays() + "'" +
            ", region='" + getRegion() + "'" +
            ", ville='" + getVille() + "'" +
            ", telephone1='" + getTelephone1() + "'" +
            ", telephone2='" + getTelephone2() + "'" +
            "}";
    }
}
