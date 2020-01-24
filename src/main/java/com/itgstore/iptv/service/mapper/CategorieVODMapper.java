package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.CategorieVODDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieVOD} and its DTO {@link CategorieVODDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieVODMapper extends EntityMapper<CategorieVODDTO, CategorieVOD> {



    default CategorieVOD fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieVOD categorieVOD = new CategorieVOD();
        categorieVOD.setId(id);
        return categorieVOD;
    }
}
