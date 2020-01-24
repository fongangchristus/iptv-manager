package com.itgstore.iptv.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class CategorieVODDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieVODDTO.class);
        CategorieVODDTO categorieVODDTO1 = new CategorieVODDTO();
        categorieVODDTO1.setId(1L);
        CategorieVODDTO categorieVODDTO2 = new CategorieVODDTO();
        assertThat(categorieVODDTO1).isNotEqualTo(categorieVODDTO2);
        categorieVODDTO2.setId(categorieVODDTO1.getId());
        assertThat(categorieVODDTO1).isEqualTo(categorieVODDTO2);
        categorieVODDTO2.setId(2L);
        assertThat(categorieVODDTO1).isNotEqualTo(categorieVODDTO2);
        categorieVODDTO1.setId(null);
        assertThat(categorieVODDTO1).isNotEqualTo(categorieVODDTO2);
    }
}
