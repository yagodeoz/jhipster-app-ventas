package com.appec.ventasweb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CuentaCliente.
 */
@Document(collection = "cuenta_cliente")
public class CuentaCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("usuario")
    private String usuario;

    @Field("clave")
    private String clave;

    @Field("avatar")
    private String avatar;

    @Field("fecha_ultimo_acceso")
    private LocalDate fechaUltimoAcceso;

    @Field("nombres")
    private String nombres;

    @Field("apellidos")
    private String apellidos;

    @DBRef
    @Field("carritoCliente")
    private CarritoCliente carritoCliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public CuentaCliente usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public CuentaCliente clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAvatar() {
        return avatar;
    }

    public CuentaCliente avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public CuentaCliente fechaUltimoAcceso(LocalDate fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
        return this;
    }

    public void setFechaUltimoAcceso(LocalDate fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    public String getNombres() {
        return nombres;
    }

    public CuentaCliente nombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public CuentaCliente apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public CarritoCliente getCarritoCliente() {
        return carritoCliente;
    }

    public CuentaCliente carritoCliente(CarritoCliente carritoCliente) {
        this.carritoCliente = carritoCliente;
        return this;
    }

    public void setCarritoCliente(CarritoCliente carritoCliente) {
        this.carritoCliente = carritoCliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CuentaCliente)) {
            return false;
        }
        return id != null && id.equals(((CuentaCliente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CuentaCliente{" +
            "id=" + getId() +
            ", usuario='" + getUsuario() + "'" +
            ", clave='" + getClave() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", fechaUltimoAcceso='" + getFechaUltimoAcceso() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            "}";
    }
}
