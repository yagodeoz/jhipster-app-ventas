package com.appec.ventasweb.web.rest;

import com.appec.ventasweb.AppventasservicesApp;
import com.appec.ventasweb.domain.Productos;
import com.appec.ventasweb.repository.ProductosRepository;
import com.appec.ventasweb.service.ProductosService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductosResource} REST controller.
 */
@SpringBootTest(classes = AppventasservicesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductosResourceIT {

    private static final Long DEFAULT_ID_EMPRESA = 1L;
    private static final Long UPDATED_ID_EMPRESA = 2L;

    private static final String DEFAULT_DESCRIPCION_PRODUCTO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_PRODUCTO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN_PRODUCTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_PRODUCTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_PRODUCTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_PRODUCTO_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_PRECIO_PRODUCTO = 1D;
    private static final Double UPDATED_PRECIO_PRODUCTO = 2D;

    private static final Double DEFAULT_VALOR_IVA = 1D;
    private static final Double UPDATED_VALOR_IVA = 2D;

    private static final Double DEFAULT_VALOR_ICE = 1D;
    private static final Double UPDATED_VALOR_ICE = 2D;

    private static final Double DEFAULT_DESCUENTO = 1D;
    private static final Double UPDATED_DESCUENTO = 2D;

    private static final Long DEFAULT_NO_VISITAS = 1L;
    private static final Long UPDATED_NO_VISITAS = 2L;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private ProductosService productosService;

    @Autowired
    private MockMvc restProductosMockMvc;

    private Productos productos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Productos createEntity() {
        Productos productos = new Productos()
            .idEmpresa(DEFAULT_ID_EMPRESA)
            .descripcionProducto(DEFAULT_DESCRIPCION_PRODUCTO)
            .imagenProducto(DEFAULT_IMAGEN_PRODUCTO)
            .imagenProductoContentType(DEFAULT_IMAGEN_PRODUCTO_CONTENT_TYPE)
            .precioProducto(DEFAULT_PRECIO_PRODUCTO)
            .valorIva(DEFAULT_VALOR_IVA)
            .valorICE(DEFAULT_VALOR_ICE)
            .descuento(DEFAULT_DESCUENTO)
            .noVisitas(DEFAULT_NO_VISITAS);
        return productos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Productos createUpdatedEntity() {
        Productos productos = new Productos()
            .idEmpresa(UPDATED_ID_EMPRESA)
            .descripcionProducto(UPDATED_DESCRIPCION_PRODUCTO)
            .imagenProducto(UPDATED_IMAGEN_PRODUCTO)
            .imagenProductoContentType(UPDATED_IMAGEN_PRODUCTO_CONTENT_TYPE)
            .precioProducto(UPDATED_PRECIO_PRODUCTO)
            .valorIva(UPDATED_VALOR_IVA)
            .valorICE(UPDATED_VALOR_ICE)
            .descuento(UPDATED_DESCUENTO)
            .noVisitas(UPDATED_NO_VISITAS);
        return productos;
    }

    @BeforeEach
    public void initTest() {
        productosRepository.deleteAll();
        productos = createEntity();
    }

    @Test
    public void createProductos() throws Exception {
        int databaseSizeBeforeCreate = productosRepository.findAll().size();
        // Create the Productos
        restProductosMockMvc.perform(post("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productos)))
            .andExpect(status().isCreated());

        // Validate the Productos in the database
        List<Productos> productosList = productosRepository.findAll();
        assertThat(productosList).hasSize(databaseSizeBeforeCreate + 1);
        Productos testProductos = productosList.get(productosList.size() - 1);
        assertThat(testProductos.getIdEmpresa()).isEqualTo(DEFAULT_ID_EMPRESA);
        assertThat(testProductos.getDescripcionProducto()).isEqualTo(DEFAULT_DESCRIPCION_PRODUCTO);
        assertThat(testProductos.getImagenProducto()).isEqualTo(DEFAULT_IMAGEN_PRODUCTO);
        assertThat(testProductos.getImagenProductoContentType()).isEqualTo(DEFAULT_IMAGEN_PRODUCTO_CONTENT_TYPE);
        assertThat(testProductos.getPrecioProducto()).isEqualTo(DEFAULT_PRECIO_PRODUCTO);
        assertThat(testProductos.getValorIva()).isEqualTo(DEFAULT_VALOR_IVA);
        assertThat(testProductos.getValorICE()).isEqualTo(DEFAULT_VALOR_ICE);
        assertThat(testProductos.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
        assertThat(testProductos.getNoVisitas()).isEqualTo(DEFAULT_NO_VISITAS);
    }

    @Test
    public void createProductosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productosRepository.findAll().size();

        // Create the Productos with an existing ID
        productos.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductosMockMvc.perform(post("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productos)))
            .andExpect(status().isBadRequest());

        // Validate the Productos in the database
        List<Productos> productosList = productosRepository.findAll();
        assertThat(productosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllProductos() throws Exception {
        // Initialize the database
        productosRepository.save(productos);

        // Get all the productosList
        restProductosMockMvc.perform(get("/api/productos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productos.getId())))
            .andExpect(jsonPath("$.[*].idEmpresa").value(hasItem(DEFAULT_ID_EMPRESA.intValue())))
            .andExpect(jsonPath("$.[*].descripcionProducto").value(hasItem(DEFAULT_DESCRIPCION_PRODUCTO)))
            .andExpect(jsonPath("$.[*].imagenProductoContentType").value(hasItem(DEFAULT_IMAGEN_PRODUCTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagenProducto").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_PRODUCTO))))
            .andExpect(jsonPath("$.[*].precioProducto").value(hasItem(DEFAULT_PRECIO_PRODUCTO.doubleValue())))
            .andExpect(jsonPath("$.[*].valorIva").value(hasItem(DEFAULT_VALOR_IVA.doubleValue())))
            .andExpect(jsonPath("$.[*].valorICE").value(hasItem(DEFAULT_VALOR_ICE.doubleValue())))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO.doubleValue())))
            .andExpect(jsonPath("$.[*].noVisitas").value(hasItem(DEFAULT_NO_VISITAS.intValue())));
    }
    
    @Test
    public void getProductos() throws Exception {
        // Initialize the database
        productosRepository.save(productos);

        // Get the productos
        restProductosMockMvc.perform(get("/api/productos/{id}", productos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productos.getId()))
            .andExpect(jsonPath("$.idEmpresa").value(DEFAULT_ID_EMPRESA.intValue()))
            .andExpect(jsonPath("$.descripcionProducto").value(DEFAULT_DESCRIPCION_PRODUCTO))
            .andExpect(jsonPath("$.imagenProductoContentType").value(DEFAULT_IMAGEN_PRODUCTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagenProducto").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_PRODUCTO)))
            .andExpect(jsonPath("$.precioProducto").value(DEFAULT_PRECIO_PRODUCTO.doubleValue()))
            .andExpect(jsonPath("$.valorIva").value(DEFAULT_VALOR_IVA.doubleValue()))
            .andExpect(jsonPath("$.valorICE").value(DEFAULT_VALOR_ICE.doubleValue()))
            .andExpect(jsonPath("$.descuento").value(DEFAULT_DESCUENTO.doubleValue()))
            .andExpect(jsonPath("$.noVisitas").value(DEFAULT_NO_VISITAS.intValue()));
    }
    @Test
    public void getNonExistingProductos() throws Exception {
        // Get the productos
        restProductosMockMvc.perform(get("/api/productos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductos() throws Exception {
        // Initialize the database
        productosService.save(productos);

        int databaseSizeBeforeUpdate = productosRepository.findAll().size();

        // Update the productos
        Productos updatedProductos = productosRepository.findById(productos.getId()).get();
        updatedProductos
            .idEmpresa(UPDATED_ID_EMPRESA)
            .descripcionProducto(UPDATED_DESCRIPCION_PRODUCTO)
            .imagenProducto(UPDATED_IMAGEN_PRODUCTO)
            .imagenProductoContentType(UPDATED_IMAGEN_PRODUCTO_CONTENT_TYPE)
            .precioProducto(UPDATED_PRECIO_PRODUCTO)
            .valorIva(UPDATED_VALOR_IVA)
            .valorICE(UPDATED_VALOR_ICE)
            .descuento(UPDATED_DESCUENTO)
            .noVisitas(UPDATED_NO_VISITAS);

        restProductosMockMvc.perform(put("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductos)))
            .andExpect(status().isOk());

        // Validate the Productos in the database
        List<Productos> productosList = productosRepository.findAll();
        assertThat(productosList).hasSize(databaseSizeBeforeUpdate);
        Productos testProductos = productosList.get(productosList.size() - 1);
        assertThat(testProductos.getIdEmpresa()).isEqualTo(UPDATED_ID_EMPRESA);
        assertThat(testProductos.getDescripcionProducto()).isEqualTo(UPDATED_DESCRIPCION_PRODUCTO);
        assertThat(testProductos.getImagenProducto()).isEqualTo(UPDATED_IMAGEN_PRODUCTO);
        assertThat(testProductos.getImagenProductoContentType()).isEqualTo(UPDATED_IMAGEN_PRODUCTO_CONTENT_TYPE);
        assertThat(testProductos.getPrecioProducto()).isEqualTo(UPDATED_PRECIO_PRODUCTO);
        assertThat(testProductos.getValorIva()).isEqualTo(UPDATED_VALOR_IVA);
        assertThat(testProductos.getValorICE()).isEqualTo(UPDATED_VALOR_ICE);
        assertThat(testProductos.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
        assertThat(testProductos.getNoVisitas()).isEqualTo(UPDATED_NO_VISITAS);
    }

    @Test
    public void updateNonExistingProductos() throws Exception {
        int databaseSizeBeforeUpdate = productosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductosMockMvc.perform(put("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productos)))
            .andExpect(status().isBadRequest());

        // Validate the Productos in the database
        List<Productos> productosList = productosRepository.findAll();
        assertThat(productosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductos() throws Exception {
        // Initialize the database
        productosService.save(productos);

        int databaseSizeBeforeDelete = productosRepository.findAll().size();

        // Delete the productos
        restProductosMockMvc.perform(delete("/api/productos/{id}", productos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Productos> productosList = productosRepository.findAll();
        assertThat(productosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
