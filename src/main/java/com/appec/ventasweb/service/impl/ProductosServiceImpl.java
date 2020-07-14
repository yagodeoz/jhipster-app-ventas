package com.appec.ventasweb.service.impl;

import com.appec.ventasweb.service.ProductosService;
import com.appec.ventasweb.domain.Productos;
import com.appec.ventasweb.repository.ProductosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Productos}.
 */
@Service
public class ProductosServiceImpl implements ProductosService {

    private final Logger log = LoggerFactory.getLogger(ProductosServiceImpl.class);

    private final ProductosRepository productosRepository;

    public ProductosServiceImpl(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public Productos save(Productos productos) {
        log.debug("Request to save Productos : {}", productos);
        return productosRepository.save(productos);
    }

    @Override
    public Page<Productos> findAll(Pageable pageable) {
        log.debug("Request to get all Productos");
        return productosRepository.findAll(pageable);
    }


    @Override
    public Optional<Productos> findOne(String id) {
        log.debug("Request to get Productos : {}", id);
        return productosRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Productos : {}", id);
        productosRepository.deleteById(id);
    }
}
