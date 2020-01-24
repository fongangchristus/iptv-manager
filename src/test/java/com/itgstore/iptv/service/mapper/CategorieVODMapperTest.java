package com.itgstore.iptv.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CategorieVODMapperTest {

    private CategorieVODMapper categorieVODMapper;

    @BeforeEach
    public void setUp() {
        categorieVODMapper = new CategorieVODMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(categorieVODMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieVODMapper.fromId(null)).isNull();
    }
}
