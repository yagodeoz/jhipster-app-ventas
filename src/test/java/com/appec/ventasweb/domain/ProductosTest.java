package com.appec.ventasweb.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.appec.ventasweb.web.rest.TestUtil;

public class ProductosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Productos.class);
        Productos productos1 = new Productos();
        productos1.setId("id1");
        Productos productos2 = new Productos();
        productos2.setId(productos1.getId());
        assertThat(productos1).isEqualTo(productos2);
        productos2.setId("id2");
        assertThat(productos1).isNotEqualTo(productos2);
        productos1.setId(null);
        assertThat(productos1).isNotEqualTo(productos2);
    }
}
