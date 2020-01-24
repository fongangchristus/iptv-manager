package com.itgstore.iptv.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.itgstore.iptv.web.rest.TestUtil;

public class ChaineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChaineDTO.class);
        ChaineDTO chaineDTO1 = new ChaineDTO();
        chaineDTO1.setId(1L);
        ChaineDTO chaineDTO2 = new ChaineDTO();
        assertThat(chaineDTO1).isNotEqualTo(chaineDTO2);
        chaineDTO2.setId(chaineDTO1.getId());
        assertThat(chaineDTO1).isEqualTo(chaineDTO2);
        chaineDTO2.setId(2L);
        assertThat(chaineDTO1).isNotEqualTo(chaineDTO2);
        chaineDTO1.setId(null);
        assertThat(chaineDTO1).isNotEqualTo(chaineDTO2);
    }
}
