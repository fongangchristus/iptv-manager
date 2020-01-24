package com.itgstore.iptv.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CategorieTVMapperTest {

    private CategorieTVMapper categorieTVMapper;

    @BeforeEach
    public void setUp() {
        categorieTVMapper = new CategorieTVMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(categorieTVMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieTVMapper.fromId(null)).isNull();
    }
}
