package com.microservice.references.service;

import com.microservice.references.entities.Referencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReferenciaService {

    Page<Referencia> findAll(Pageable pageable);

    Referencia findById(String id);

    void save(Referencia referencia);

    void delete(String id);

    void update(String id, Referencia referencia);

    // Lista de referencias por linea
}
