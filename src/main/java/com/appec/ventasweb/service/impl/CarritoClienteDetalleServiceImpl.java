package com.appec.ventasweb.service.impl;

import com.appec.ventasweb.service.CarritoClienteDetalleService;
import com.appec.ventasweb.domain.CarritoClienteDetalle;
import com.appec.ventasweb.repository.CarritoClienteDetalleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CarritoClienteDetalle}.
 */
@Service
public class CarritoClienteDetalleServiceImpl implements CarritoClienteDetalleService {

    private final Logger log = LoggerFactory.getLogger(CarritoClienteDetalleServiceImpl.class);

    private final CarritoClienteDetalleRepository carritoClienteDetalleRepository;

    public CarritoClienteDetalleServiceImpl(CarritoClienteDetalleRepository carritoClienteDetalleRepository) {
        this.carritoClienteDetalleRepository = carritoClienteDetalleRepository;
    }

    @Override
    public CarritoClienteDetalle save(CarritoClienteDetalle carritoClienteDetalle) {
        log.debug("Request to save CarritoClienteDetalle : {}", carritoClienteDetalle);
        return carritoClienteDetalleRepository.save(carritoClienteDetalle);
    }

    @Override
    public Page<CarritoClienteDetalle> findAll(Pageable pageable) {
        log.debug("Request to get all CarritoClienteDetalles");
        return carritoClienteDetalleRepository.findAll(pageable);
    }


    @Override
    public Optional<CarritoClienteDetalle> findOne(String id) {
        log.debug("Request to get CarritoClienteDetalle : {}", id);
        return carritoClienteDetalleRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete CarritoClienteDetalle : {}", id);
        carritoClienteDetalleRepository.deleteById(id);
    }
}
