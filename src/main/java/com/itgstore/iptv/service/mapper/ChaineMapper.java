package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.ChaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Chaine} and its DTO {@link ChaineDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieTVMapper.class})
public interface ChaineMapper extends EntityMapper<ChaineDTO, Chaine> {

    @Mapping(source = "categorieT.id", target = "categorieTId")
    ChaineDTO toDto(Chaine chaine);

    @Mapping(source = "categorieTId", target = "categorieT")
    @Mapping(target = "packages", ignore = true)
    @Mapping(target = "removePackages", ignore = true)
    Chaine toEntity(ChaineDTO chaineDTO);

    default Chaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chaine chaine = new Chaine();
        chaine.setId(id);
        return chaine;
    }
}
