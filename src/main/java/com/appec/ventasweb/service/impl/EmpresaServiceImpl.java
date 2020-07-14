package com.appec.ventasweb.service.impl;

import com.appec.ventasweb.service.EmpresaService;
import com.appec.ventasweb.domain.Empresa;
import com.appec.ventasweb.repository.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Empresa}.
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa save(Empresa empresa) {
        log.debug("Request to save Empresa : {}", empresa);
        return empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        log.debug("Request to get all Empresas");
        return empresaRepository.findAll();
    }


    @Override
    public Optional<Empresa> findOne(String id) {
        log.debug("Request to get Empresa : {}", id);
        return empresaRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Empresa : {}", id);
        empresaRepository.deleteById(id);
    }
}
