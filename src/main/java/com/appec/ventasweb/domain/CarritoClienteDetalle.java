package com.appec.ventasweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

/**
 * A CarritoClienteDetalle.
 */
@Document(collection = "carrito_cliente_detalle")
public class CarritoClienteDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cantidad")
    private Long cantidad;

    @Field("total")
    private Double total;

    @DBRef
    @Field("carritoCliente")
    @JsonIgnoreProperties(value = "carritoClienteDetalles", allowSetters = true)
    private CarritoCliente carritoCliente;

    @DBRef
    @Field("productos")
    @JsonIgnoreProperties(value = "carritoClienteDetalles", allowSetters = true)
    private Productos productos;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public CarritoClienteDetalle cantidad(Long cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public CarritoClienteDetalle total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public CarritoCliente getCarritoCliente() {
        return carritoCliente;
    }

    public CarritoClienteDetalle carritoCliente(CarritoCliente carritoCliente) {
        this.carritoCliente = carritoCliente;
        return this;
    }

    public void setCarritoCliente(CarritoCliente carritoCliente) {
        this.carritoCliente = carritoCliente;
    }

    public Productos getProductos() {
        return productos;
    }

    public CarritoClienteDetalle productos(Productos productos) {
        this.productos = productos;
        return this;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarritoClienteDetalle)) {
            return false;
        }
        return id != null && id.equals(((CarritoClienteDetalle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarritoClienteDetalle{" +
            "id=" + getId() +
            ", cantidad=" + getCantidad() +
            ", total=" + getTotal() +
            "}";
    }
}
