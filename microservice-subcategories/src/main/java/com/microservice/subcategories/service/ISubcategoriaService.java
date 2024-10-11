package com.microservice.subcategories.service;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;

import java.util.List;

public interface ISubcategoriaService {

    List<Subcategoria> findAll();

    Subcategoria findById(SubcategoriaId id);

    void save(Subcategoria subcategoria);

    void delete(SubcategoriaId id);

    void update(SubcategoriaId id, Subcategoria subcategoria);

    List<Subcategoria> findSubcategoriaById(SubcategoriaId id);
}
