package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.RessourceVODDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RessourceVOD} and its DTO {@link RessourceVODDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieVODMapper.class})
public interface RessourceVODMapper extends EntityMapper<RessourceVODDTO, RessourceVOD> {

    @Mapping(source = "categorieVOD.id", target = "categorieVODId")
    RessourceVODDTO toDto(RessourceVOD ressourceVOD);

    @Mapping(source = "categorieVODId", target = "categorieVOD")
    @Mapping(target = "packages", ignore = true)
    @Mapping(target = "removePackages", ignore = true)
    RessourceVOD toEntity(RessourceVODDTO ressourceVODDTO);

    default RessourceVOD fromId(Long id) {
        if (id == null) {
            return null;
        }
        RessourceVOD ressourceVOD = new RessourceVOD();
        ressourceVOD.setId(id);
        return ressourceVOD;
    }
}
