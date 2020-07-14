package com.appec.ventasweb.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.appec.ventasweb.web.rest.TestUtil;

public class CuentaClienteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CuentaCliente.class);
        CuentaCliente cuentaCliente1 = new CuentaCliente();
        cuentaCliente1.setId("id1");
        CuentaCliente cuentaCliente2 = new CuentaCliente();
        cuentaCliente2.setId(cuentaCliente1.getId());
        assertThat(cuentaCliente1).isEqualTo(cuentaCliente2);
        cuentaCliente2.setId("id2");
        assertThat(cuentaCliente1).isNotEqualTo(cuentaCliente2);
        cuentaCliente1.setId(null);
        assertThat(cuentaCliente1).isNotEqualTo(cuentaCliente2);
    }
}
