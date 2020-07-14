package com.appec.ventasweb.web.rest;

import com.appec.ventasweb.domain.Empresa;
import com.appec.ventasweb.service.EmpresaService;
import com.appec.ventasweb.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.appec.ventasweb.domain.Empresa}.
 */
@RestController
@RequestMapping("/api")
public class EmpresaResource {

    private final Logger log = LoggerFactory.getLogger(EmpresaResource.class);

    private static final String ENTITY_NAME = "empresa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpresaService empresaService;

    public EmpresaResource(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    /**
     * {@code POST  /empresas} : Create a new empresa.
     *
     * @param empresa the empresa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empresa, or with status {@code 400 (Bad Request)} if the empresa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/empresas")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) throws URISyntaxException {
        log.debug("REST request to save Empresa : {}", empresa);
        if (empresa.getId() != null) {
            throw new BadRequestAlertException("A new empresa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Empresa result = empresaService.save(empresa);
        return ResponseEntity.created(new URI("/api/empresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /empresas} : Updates an existing empresa.
     *
     * @param empresa the empresa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empresa,
     * or with status {@code 400 (Bad Request)} if the empresa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empresa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/empresas")
    public ResponseEntity<Empresa> updateEmpresa(@RequestBody Empresa empresa) throws URISyntaxException {
        log.debug("REST request to update Empresa : {}", empresa);
        if (empresa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Empresa result = empresaService.save(empresa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empresa.getId()))
            .body(result);
    }

    /**
     * {@code GET  /empresas} : get all the empresas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empresas in body.
     */
    @GetMapping("/empresas")
    public List<Empresa> getAllEmpresas() {
        log.debug("REST request to get all Empresas");
        return empresaService.findAll();
    }

    /**
     * {@code GET  /empresas/:id} : get the "id" empresa.
     *
     * @param id the id of the empresa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empresa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable String id) {
        log.debug("REST request to get Empresa : {}", id);
        Optional<Empresa> empresa = empresaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(empresa);
    }

    /**
     * {@code DELETE  /empresas/:id} : delete the "id" empresa.
     *
     * @param id the id of the empresa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable String id) {
        log.debug("REST request to delete Empresa : {}", id);
        empresaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
