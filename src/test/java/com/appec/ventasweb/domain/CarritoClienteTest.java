package com.appec.ventasweb.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.appec.ventasweb.web.rest.TestUtil;

public class CarritoClienteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarritoCliente.class);
        CarritoCliente carritoCliente1 = new CarritoCliente();
        carritoCliente1.setId("id1");
        CarritoCliente carritoCliente2 = new CarritoCliente();
        carritoCliente2.setId(carritoCliente1.getId());
        assertThat(carritoCliente1).isEqualTo(carritoCliente2);
        carritoCliente2.setId("id2");
        assertThat(carritoCliente1).isNotEqualTo(carritoCliente2);
        carritoCliente1.setId(null);
        assertThat(carritoCliente1).isNotEqualTo(carritoCliente2);
    }
}
