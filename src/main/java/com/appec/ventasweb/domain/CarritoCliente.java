package com.appec.ventasweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A CarritoCliente.
 */
@Document(collection = "carrito_cliente")
public class CarritoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("fecha_acceso")
    private LocalDate fechaAcceso;

    @Field("fecha_ultima_compra")
    private LocalDate fechaUltimaCompra;

    @DBRef
    @Field("carritoClienteDetalle")
    private Set<CarritoClienteDetalle> carritoClienteDetalles = new HashSet<>();

    @DBRef
    @Field("cuentaCliente")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private CuentaCliente cuentaCliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaAcceso() {
        return fechaAcceso;
    }

    public CarritoCliente fechaAcceso(LocalDate fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
        return this;
    }

    public void setFechaAcceso(LocalDate fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public LocalDate getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public CarritoCliente fechaUltimaCompra(LocalDate fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
        return this;
    }

    public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public Set<CarritoClienteDetalle> getCarritoClienteDetalles() {
        return carritoClienteDetalles;
    }

    public CarritoCliente carritoClienteDetalles(Set<CarritoClienteDetalle> carritoClienteDetalles) {
        this.carritoClienteDetalles = carritoClienteDetalles;
        return this;
    }

    public CarritoCliente addCarritoClienteDetalle(CarritoClienteDetalle carritoClienteDetalle) {
        this.carritoClienteDetalles.add(carritoClienteDetalle);
        carritoClienteDetalle.setCarritoCliente(this);
        return this;
    }

    public CarritoCliente removeCarritoClienteDetalle(CarritoClienteDetalle carritoClienteDetalle) {
        this.carritoClienteDetalles.remove(carritoClienteDetalle);
        carritoClienteDetalle.setCarritoCliente(null);
        return this;
    }

    public void setCarritoClienteDetalles(Set<CarritoClienteDetalle> carritoClienteDetalles) {
        this.carritoClienteDetalles = carritoClienteDetalles;
    }

    public CuentaCliente getCuentaCliente() {
        return cuentaCliente;
    }

    public CarritoCliente cuentaCliente(CuentaCliente cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
        return this;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarritoCliente)) {
            return false;
        }
        return id != null && id.equals(((CarritoCliente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarritoCliente{" +
            "id=" + getId() +
            ", fechaAcceso='" + getFechaAcceso() + "'" +
            ", fechaUltimaCompra='" + getFechaUltimaCompra() + "'" +
            "}";
    }
}
