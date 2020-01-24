package com.itgstore.iptv.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class CategorieTVTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieTV.class);
        CategorieTV categorieTV1 = new CategorieTV();
        categorieTV1.setId(1L);
        CategorieTV categorieTV2 = new CategorieTV();
        categorieTV2.setId(categorieTV1.getId());
        assertThat(categorieTV1).isEqualTo(categorieTV2);
        categorieTV2.setId(2L);
        assertThat(categorieTV1).isNotEqualTo(categorieTV2);
        categorieTV1.setId(null);
        assertThat(categorieTV1).isNotEqualTo(categorieTV2);
    }
}
