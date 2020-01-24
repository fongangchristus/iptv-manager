package com.itgstore.iptv.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class CategorieTVDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieTVDTO.class);
        CategorieTVDTO categorieTVDTO1 = new CategorieTVDTO();
        categorieTVDTO1.setId(1L);
        CategorieTVDTO categorieTVDTO2 = new CategorieTVDTO();
        assertThat(categorieTVDTO1).isNotEqualTo(categorieTVDTO2);
        categorieTVDTO2.setId(categorieTVDTO1.getId());
        assertThat(categorieTVDTO1).isEqualTo(categorieTVDTO2);
        categorieTVDTO2.setId(2L);
        assertThat(categorieTVDTO1).isNotEqualTo(categorieTVDTO2);
        categorieTVDTO1.setId(null);
        assertThat(categorieTVDTO1).isNotEqualTo(categorieTVDTO2);
    }
}
