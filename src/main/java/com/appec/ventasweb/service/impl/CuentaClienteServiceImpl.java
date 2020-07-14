package com.appec.ventasweb.service.impl;

import com.appec.ventasweb.service.CuentaClienteService;
import com.appec.ventasweb.domain.CuentaCliente;
import com.appec.ventasweb.repository.CuentaClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CuentaCliente}.
 */
@Service
public class CuentaClienteServiceImpl implements CuentaClienteService {

    private final Logger log = LoggerFactory.getLogger(CuentaClienteServiceImpl.class);

    private final CuentaClienteRepository cuentaClienteRepository;

    public CuentaClienteServiceImpl(CuentaClienteRepository cuentaClienteRepository) {
        this.cuentaClienteRepository = cuentaClienteRepository;
    }

    @Override
    public CuentaCliente save(CuentaCliente cuentaCliente) {
        log.debug("Request to save CuentaCliente : {}", cuentaCliente);
        return cuentaClienteRepository.save(cuentaCliente);
    }

    @Override
    public List<CuentaCliente> findAll() {
        log.debug("Request to get all CuentaClientes");
        return cuentaClienteRepository.findAll();
    }


    @Override
    public Optional<CuentaCliente> findOne(String id) {
        log.debug("Request to get CuentaCliente : {}", id);
        return cuentaClienteRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete CuentaCliente : {}", id);
        cuentaClienteRepository.deleteById(id);
    }
}
