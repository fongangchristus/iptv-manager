package com.itgstore.iptv.service.mapper;

import com.itgstore.iptv.domain.*;
import com.itgstore.iptv.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "adresse.id", target = "adresseId")
    ClientDTO toDto(Client client);

    @Mapping(source = "adresseId", target = "adresse")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
