package com.itgstore.iptv.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ChaineMapperTest {

    private ChaineMapper chaineMapper;

    @BeforeEach
    public void setUp() {
        chaineMapper = new ChaineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(chaineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chaineMapper.fromId(null)).isNull();
    }
}
