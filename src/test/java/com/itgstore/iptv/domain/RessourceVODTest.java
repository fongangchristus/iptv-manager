package com.itgstore.iptv.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class RessourceVODTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RessourceVOD.class);
        RessourceVOD ressourceVOD1 = new RessourceVOD();
        ressourceVOD1.setId(1L);
        RessourceVOD ressourceVOD2 = new RessourceVOD();
        ressourceVOD2.setId(ressourceVOD1.getId());
        assertThat(ressourceVOD1).isEqualTo(ressourceVOD2);
        ressourceVOD2.setId(2L);
        assertThat(ressourceVOD1).isNotEqualTo(ressourceVOD2);
        ressourceVOD1.setId(null);
        assertThat(ressourceVOD1).isNotEqualTo(ressourceVOD2);
    }
}
