package com.itgstore.iptv.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class RessourceVODDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RessourceVODDTO.class);
        RessourceVODDTO ressourceVODDTO1 = new RessourceVODDTO();
        ressourceVODDTO1.setId(1L);
        RessourceVODDTO ressourceVODDTO2 = new RessourceVODDTO();
        assertThat(ressourceVODDTO1).isNotEqualTo(ressourceVODDTO2);
        ressourceVODDTO2.setId(ressourceVODDTO1.getId());
        assertThat(ressourceVODDTO1).isEqualTo(ressourceVODDTO2);
        ressourceVODDTO2.setId(2L);
        assertThat(ressourceVODDTO1).isNotEqualTo(ressourceVODDTO2);
        ressourceVODDTO1.setId(null);
        assertThat(ressourceVODDTO1).isNotEqualTo(ressourceVODDTO2);
    }
}
