package com.appec.ventasweb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Empresa.
 */
@Document(collection = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre_empresa")
    private String nombreEmpresa;

    @Field("ruc_empresa")
    private String rucEmpresa;

    @Field("direccion")
    private String direccion;

    @Field("logo")
    private byte[] logo;

    @Field("logo_content_type")
    private String logoContentType;

    @Field("color_tema")
    private String colorTema;

    @DBRef
    @Field("productos")
    private Set<Productos> productos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public Empresa nombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        return this;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public Empresa rucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
        return this;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public Empresa direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Empresa logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Empresa logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getColorTema() {
        return colorTema;
    }

    public Empresa colorTema(String colorTema) {
        this.colorTema = colorTema;
        return this;
    }

    public void setColorTema(String colorTema) {
        this.colorTema = colorTema;
    }

    public Set<Productos> getProductos() {
        return productos;
    }

    public Empresa productos(Set<Productos> productos) {
        this.productos = productos;
        return this;
    }

    public Empresa addProductos(Productos productos) {
        this.productos.add(productos);
        productos.setEmpresa(this);
        return this;
    }

    public Empresa removeProductos(Productos productos) {
        this.productos.remove(productos);
        productos.setEmpresa(null);
        return this;
    }

    public void setProductos(Set<Productos> productos) {
        this.productos = productos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Empresa)) {
            return false;
        }
        return id != null && id.equals(((Empresa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Empresa{" +
            "id=" + getId() +
            ", nombreEmpresa='" + getNombreEmpresa() + "'" +
            ", rucEmpresa='" + getRucEmpresa() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", colorTema='" + getColorTema() + "'" +
            "}";
    }
}
