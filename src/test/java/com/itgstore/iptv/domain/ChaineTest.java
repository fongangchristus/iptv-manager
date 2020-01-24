package com.itgstore.iptv.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class ChaineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chaine.class);
        Chaine chaine1 = new Chaine();
        chaine1.setId(1L);
        Chaine chaine2 = new Chaine();
        chaine2.setId(chaine1.getId());
        assertThat(chaine1).isEqualTo(chaine2);
        chaine2.setId(2L);
        assertThat(chaine1).isNotEqualTo(chaine2);
        chaine1.setId(null);
        assertThat(chaine1).isNotEqualTo(chaine2);
    }
}
