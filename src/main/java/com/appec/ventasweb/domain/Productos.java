package com.appec.ventasweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Productos.
 */
@Document(collection = "productos")
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_empresa")
    private Long idEmpresa;

    @Field("descripcion_producto")
    private String descripcionProducto;

    @Field("imagen_producto")
    private byte[] imagenProducto;

    @Field("imagen_producto_content_type")
    private String imagenProductoContentType;

    @Field("precio_producto")
    private Double precioProducto;

    @Field("valor_iva")
    private Double valorIva;

    @Field("valor_ice")
    private Double valorICE;

    @Field("descuento")
    private Double descuento;

    @Field("no_visitas")
    private Long noVisitas;

    @DBRef
    @Field("carritoClienteDetalle")
    private Set<CarritoClienteDetalle> carritoClienteDetalles = new HashSet<>();

    @DBRef
    @Field("empresa")
    @JsonIgnoreProperties(value = "productos", allowSetters = true)
    private Empresa empresa;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public Productos idEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
        return this;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public Productos descripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
        return this;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public byte[] getImagenProducto() {
        return imagenProducto;
    }

    public Productos imagenProducto(byte[] imagenProducto) {
        this.imagenProducto = imagenProducto;
        return this;
    }

    public void setImagenProducto(byte[] imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getImagenProductoContentType() {
        return imagenProductoContentType;
    }

    public Productos imagenProductoContentType(String imagenProductoContentType) {
        this.imagenProductoContentType = imagenProductoContentType;
        return this;
    }

    public void setImagenProductoContentType(String imagenProductoContentType) {
        this.imagenProductoContentType = imagenProductoContentType;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public Productos precioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
        return this;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Double getValorIva() {
        return valorIva;
    }

    public Productos valorIva(Double valorIva) {
        this.valorIva = valorIva;
        return this;
    }

    public void setValorIva(Double valorIva) {
        this.valorIva = valorIva;
    }

    public Double getValorICE() {
        return valorICE;
    }

    public Productos valorICE(Double valorICE) {
        this.valorICE = valorICE;
        return this;
    }

    public void setValorICE(Double valorICE) {
        this.valorICE = valorICE;
    }

    public Double getDescuento() {
        return descuento;
    }

    public Productos descuento(Double descuento) {
        this.descuento = descuento;
        return this;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Long getNoVisitas() {
        return noVisitas;
    }

    public Productos noVisitas(Long noVisitas) {
        this.noVisitas = noVisitas;
        return this;
    }

    public void setNoVisitas(Long noVisitas) {
        this.noVisitas = noVisitas;
    }

    public Set<CarritoClienteDetalle> getCarritoClienteDetalles() {
        return carritoClienteDetalles;
    }

    public Productos carritoClienteDetalles(Set<CarritoClienteDetalle> carritoClienteDetalles) {
        this.carritoClienteDetalles = carritoClienteDetalles;
        return this;
    }

    public Productos addCarritoClienteDetalle(CarritoClienteDetalle carritoClienteDetalle) {
        this.carritoClienteDetalles.add(carritoClienteDetalle);
        carritoClienteDetalle.setProductos(this);
        return this;
    }

    public Productos removeCarritoClienteDetalle(CarritoClienteDetalle carritoClienteDetalle) {
        this.carritoClienteDetalles.remove(carritoClienteDetalle);
        carritoClienteDetalle.setProductos(null);
        return this;
    }

    public void setCarritoClienteDetalles(Set<CarritoClienteDetalle> carritoClienteDetalles) {
        this.carritoClienteDetalles = carritoClienteDetalles;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Productos empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Productos)) {
            return false;
        }
        return id != null && id.equals(((Productos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Productos{" +
            "id=" + getId() +
            ", idEmpresa=" + getIdEmpresa() +
            ", descripcionProducto='" + getDescripcionProducto() + "'" +
            ", imagenProducto='" + getImagenProducto() + "'" +
            ", imagenProductoContentType='" + getImagenProductoContentType() + "'" +
            ", precioProducto=" + getPrecioProducto() +
            ", valorIva=" + getValorIva() +
            ", valorICE=" + getValorICE() +
            ", descuento=" + getDescuento() +
            ", noVisitas=" + getNoVisitas() +
            "}";
    }
}
