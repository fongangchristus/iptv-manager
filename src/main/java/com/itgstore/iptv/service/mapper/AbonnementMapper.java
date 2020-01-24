package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.AbonnementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Abonnement} and its DTO {@link AbonnementDTO}.
 */
@Mapper(componentModel = "spring", uses = {PackagesMapper.class, ClientMapper.class})
public interface AbonnementMapper extends EntityMapper<AbonnementDTO, Abonnement> {

    @Mapping(source = "packages.id", target = "packagesId")
    @Mapping(source = "client.id", target = "clientId")
    AbonnementDTO toDto(Abonnement abonnement);

    @Mapping(source = "packagesId", target = "packages")
    @Mapping(source = "clientId", target = "client")
    Abonnement toEntity(AbonnementDTO abonnementDTO);

    default Abonnement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Abonnement abonnement = new Abonnement();
        abonnement.setId(id);
        return abonnement;
    }
}
