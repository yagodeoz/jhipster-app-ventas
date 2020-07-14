package com.appec.ventasweb.repository;

import com.appec.ventasweb.domain.CarritoCliente;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CarritoCliente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarritoClienteRepository extends MongoRepository<CarritoCliente, String> {
}
