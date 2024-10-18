package com.microservice.references.service;

import com.microservice.references.entities.Referencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReferenciaService {

    Page<Referencia> findAll(Pageable pageable);

    Referencia findById(Long idLinea);

    void save(Referencia referencia);

    void delete(Long idLinea);

    void update(Long idLinea, Referencia referencia);

    // Lista de referencias por linea
    List<Referencia> findReferencesByLineId(Long idCategoria, Long idSubcategoria, Long idLinea);
}
