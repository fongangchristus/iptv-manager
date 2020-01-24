package com.itgstore.iptv.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class CategorieVODTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieVOD.class);
        CategorieVOD categorieVOD1 = new CategorieVOD();
        categorieVOD1.setId(1L);
        CategorieVOD categorieVOD2 = new CategorieVOD();
        categorieVOD2.setId(categorieVOD1.getId());
        assertThat(categorieVOD1).isEqualTo(categorieVOD2);
        categorieVOD2.setId(2L);
        assertThat(categorieVOD1).isNotEqualTo(categorieVOD2);
        categorieVOD1.setId(null);
        assertThat(categorieVOD1).isNotEqualTo(categorieVOD2);
    }
}
