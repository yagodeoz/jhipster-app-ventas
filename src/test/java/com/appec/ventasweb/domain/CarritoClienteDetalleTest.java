package com.appec.ventasweb.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.appec.ventasweb.web.rest.TestUtil;

public class CarritoClienteDetalleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarritoClienteDetalle.class);
        CarritoClienteDetalle carritoClienteDetalle1 = new CarritoClienteDetalle();
        carritoClienteDetalle1.setId("id1");
        CarritoClienteDetalle carritoClienteDetalle2 = new CarritoClienteDetalle();
        carritoClienteDetalle2.setId(carritoClienteDetalle1.getId());
        assertThat(carritoClienteDetalle1).isEqualTo(carritoClienteDetalle2);
        carritoClienteDetalle2.setId("id2");
        assertThat(carritoClienteDetalle1).isNotEqualTo(carritoClienteDetalle2);
        carritoClienteDetalle1.setId(null);
        assertThat(carritoClienteDetalle1).isNotEqualTo(carritoClienteDetalle2);
    }
}
