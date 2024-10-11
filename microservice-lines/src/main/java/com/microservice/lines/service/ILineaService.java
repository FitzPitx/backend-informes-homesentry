package com.microservice.lines.service;

import com.microservice.lines.entities.Linea;
import com.microservice.lines.entities.LineaId;

import java.util.List;

public interface ILineaService {

    List<Linea> findAll();

    Linea findById(LineaId id);

    void save(Linea linea);

    void delete(LineaId id);
}
