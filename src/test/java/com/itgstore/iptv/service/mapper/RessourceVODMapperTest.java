package com.itgstore.iptv.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RessourceVODMapperTest {

    private RessourceVODMapper ressourceVODMapper;

    @BeforeEach
    public void setUp() {
        ressourceVODMapper = new RessourceVODMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(ressourceVODMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ressourceVODMapper.fromId(null)).isNull();
    }
}
