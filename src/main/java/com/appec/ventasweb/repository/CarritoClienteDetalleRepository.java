package com.appec.ventasweb.repository;

import com.appec.ventasweb.domain.CarritoClienteDetalle;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CarritoClienteDetalle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarritoClienteDetalleRepository extends MongoRepository<CarritoClienteDetalle, String> {
}
