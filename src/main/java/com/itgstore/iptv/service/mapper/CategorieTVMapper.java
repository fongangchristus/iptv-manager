package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.CategorieTVDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieTV} and its DTO {@link CategorieTVDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieTVMapper extends EntityMapper<CategorieTVDTO, CategorieTV> {



    default CategorieTV fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieTV categorieTV = new CategorieTV();
        categorieTV.setId(id);
        return categorieTV;
    }
}
