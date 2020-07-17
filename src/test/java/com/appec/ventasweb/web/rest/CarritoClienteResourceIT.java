package com.appec.ventasweb.web.rest;

import com.appec.ventasweb.AppventasservicesApp;
import com.appec.ventasweb.domain.CarritoCliente;
import com.appec.ventasweb.repository.CarritoClienteRepository;
import com.appec.ventasweb.service.CarritoClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CarritoClienteResource} REST controller.
 */
@SpringBootTest(classes = AppventasservicesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CarritoClienteResourceIT {

    private static final LocalDate DEFAULT_FECHA_ACCESO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ACCESO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_ULTIMA_COMPRA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ULTIMA_COMPRA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CarritoClienteRepository carritoClienteRepository;

    @Autowired
    private CarritoClienteService carritoClienteService;

    @Autowired
    private MockMvc restCarritoClienteMockMvc;

    private CarritoCliente carritoCliente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarritoCliente createEntity() {
        CarritoCliente carritoCliente = new CarritoCliente()
            .fechaAcceso(DEFAULT_FECHA_ACCESO)
            .fechaUltimaCompra(DEFAULT_FECHA_ULTIMA_COMPRA);
        return carritoCliente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarritoCliente createUpdatedEntity() {
        CarritoCliente carritoCliente = new CarritoCliente()
            .fechaAcceso(UPDATED_FECHA_ACCESO)
            .fechaUltimaCompra(UPDATED_FECHA_ULTIMA_COMPRA);
        return carritoCliente;
    }

    @BeforeEach
    public void initTest() {
        carritoClienteRepository.deleteAll();
        carritoCliente = createEntity();
    }

    @Test
    public void createCarritoCliente() throws Exception {
        int databaseSizeBeforeCreate = carritoClienteRepository.findAll().size();
        // Create the CarritoCliente
        restCarritoClienteMockMvc.perform(post("/api/carrito-clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carritoCliente)))
            .andExpect(status().isCreated());

        // Validate the CarritoCliente in the database
        List<CarritoCliente> carritoClienteList = carritoClienteRepository.findAll();
        assertThat(carritoClienteList).hasSize(databaseSizeBeforeCreate + 1);
        CarritoCliente testCarritoCliente = carritoClienteList.get(carritoClienteList.size() - 1);
        assertThat(testCarritoCliente.getFechaAcceso()).isEqualTo(DEFAULT_FECHA_ACCESO);
        assertThat(testCarritoCliente.getFechaUltimaCompra()).isEqualTo(DEFAULT_FECHA_ULTIMA_COMPRA);
    }

    @Test
    public void createCarritoClienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carritoClienteRepository.findAll().size();

        // Create the CarritoCliente with an existing ID
        carritoCliente.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarritoClienteMockMvc.perform(post("/api/carrito-clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carritoCliente)))
            .andExpect(status().isBadRequest());

        // Validate the CarritoCliente in the database
        List<CarritoCliente> carritoClienteList = carritoClienteRepository.findAll();
        assertThat(carritoClienteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCarritoClientes() throws Exception {
        // Initialize the database
        carritoClienteRepository.save(carritoCliente);

        // Get all the carritoClienteList
        restCarritoClienteMockMvc.perform(get("/api/carrito-clientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carritoCliente.getId())))
            .andExpect(jsonPath("$.[*].fechaAcceso").value(hasItem(DEFAULT_FECHA_ACCESO.toString())))
            .andExpect(jsonPath("$.[*].fechaUltimaCompra").value(hasItem(DEFAULT_FECHA_ULTIMA_COMPRA.toString())));
    }
    
    @Test
    public void getCarritoCliente() throws Exception {
        // Initialize the database
        carritoClienteRepository.save(carritoCliente);

        // Get the carritoCliente
        restCarritoClienteMockMvc.perform(get("/api/carrito-clientes/{id}", carritoCliente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(carritoCliente.getId()))
            .andExpect(jsonPath("$.fechaAcceso").value(DEFAULT_FECHA_ACCESO.toString()))
            .andExpect(jsonPath("$.fechaUltimaCompra").value(DEFAULT_FECHA_ULTIMA_COMPRA.toString()));
    }
    @Test
    public void getNonExistingCarritoCliente() throws Exception {
        // Get the carritoCliente
        restCarritoClienteMockMvc.perform(get("/api/carrito-clientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCarritoCliente() throws Exception {
        // Initialize the database
        carritoClienteService.save(carritoCliente);

        int databaseSizeBeforeUpdate = carritoClienteRepository.findAll().size();

        // Update the carritoCliente
        CarritoCliente updatedCarritoCliente = carritoClienteRepository.findById(carritoCliente.getId()).get();
        updatedCarritoCliente
            .fechaAcceso(UPDATED_FECHA_ACCESO)
            .fechaUltimaCompra(UPDATED_FECHA_ULTIMA_COMPRA);

        restCarritoClienteMockMvc.perform(put("/api/carrito-clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCarritoCliente)))
            .andExpect(status().isOk());

        // Validate the CarritoCliente in the database
        List<CarritoCliente> carritoClienteList = carritoClienteRepository.findAll();
        assertThat(carritoClienteList).hasSize(databaseSizeBeforeUpdate);
        CarritoCliente testCarritoCliente = carritoClienteList.get(carritoClienteList.size() - 1);
        assertThat(testCarritoCliente.getFechaAcceso()).isEqualTo(UPDATED_FECHA_ACCESO);
        assertThat(testCarritoCliente.getFechaUltimaCompra()).isEqualTo(UPDATED_FECHA_ULTIMA_COMPRA);
    }

    @Test
    public void updateNonExistingCarritoCliente() throws Exception {
        int databaseSizeBeforeUpdate = carritoClienteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarritoClienteMockMvc.perform(put("/api/carrito-clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carritoCliente)))
            .andExpect(status().isBadRequest());

        // Validate the CarritoCliente in the database
        List<CarritoCliente> carritoClienteList = carritoClienteRepository.findAll();
        assertThat(carritoClienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCarritoCliente() throws Exception {
        // Initialize the database
        carritoClienteService.save(carritoCliente);

        int databaseSizeBeforeDelete = carritoClienteRepository.findAll().size();

        // Delete the carritoCliente
        restCarritoClienteMockMvc.perform(delete("/api/carrito-clientes/{id}", carritoCliente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CarritoCliente> carritoClienteList = carritoClienteRepository.findAll();
        assertThat(carritoClienteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
