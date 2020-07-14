package com.appec.ventasweb.service.impl;

import com.appec.ventasweb.service.CarritoClienteService;
import com.appec.ventasweb.domain.CarritoCliente;
import com.appec.ventasweb.repository.CarritoClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link CarritoCliente}.
 */
@Service
public class CarritoClienteServiceImpl implements CarritoClienteService {

    private final Logger log = LoggerFactory.getLogger(CarritoClienteServiceImpl.class);

    private final CarritoClienteRepository carritoClienteRepository;

    public CarritoClienteServiceImpl(CarritoClienteRepository carritoClienteRepository) {
        this.carritoClienteRepository = carritoClienteRepository;
    }

    @Override
    public CarritoCliente save(CarritoCliente carritoCliente) {
        log.debug("Request to save CarritoCliente : {}", carritoCliente);
        return carritoClienteRepository.save(carritoCliente);
    }

    @Override
    public List<CarritoCliente> findAll() {
        log.debug("Request to get all CarritoClientes");
        return carritoClienteRepository.findAll();
    }



    /**
     *  Get all the carritoClientes where CuentaCliente is {@code null}.
     *  @return the list of entities.
     */
    public List<CarritoCliente> findAllWhereCuentaClienteIsNull() {
        log.debug("Request to get all carritoClientes where CuentaCliente is null");
        return StreamSupport
            .stream(carritoClienteRepository.findAll().spliterator(), false)
            .filter(carritoCliente -> carritoCliente.getCuentaCliente() == null)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<CarritoCliente> findOne(String id) {
        log.debug("Request to get CarritoCliente : {}", id);
        return carritoClienteRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete CarritoCliente : {}", id);
        carritoClienteRepository.deleteById(id);
    }
}
