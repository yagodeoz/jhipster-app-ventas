package com.appec.ventasweb.repository;

import com.appec.ventasweb.domain.CuentaCliente;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CuentaCliente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CuentaClienteRepository extends MongoRepository<CuentaCliente, String> {
}
