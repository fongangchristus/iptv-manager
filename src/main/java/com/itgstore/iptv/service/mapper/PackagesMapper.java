package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.PackagesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Packages} and its DTO {@link PackagesDTO}.
 */
@Mapper(componentModel = "spring", uses = {ChaineMapper.class, RessourceVODMapper.class})
public interface PackagesMapper extends EntityMapper<PackagesDTO, Packages> {


    @Mapping(target = "removePackagechaine", ignore = true)
    @Mapping(target = "removePackageVOD", ignore = true)

    default Packages fromId(Long id) {
        if (id == null) {
            return null;
        }
        Packages packages = new Packages();
        packages.setId(id);
        return packages;
    }
}
