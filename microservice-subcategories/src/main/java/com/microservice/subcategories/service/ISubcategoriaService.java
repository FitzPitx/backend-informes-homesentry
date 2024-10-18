package com.microservice.subcategories.service;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import com.microservice.subcategories.http.response.LineasBySubcategoriesResponse;

import java.util.List;

public interface ISubcategoriaService {

    List<Subcategoria> findAll();

    Subcategoria findById(SubcategoriaId id);

    void save(Subcategoria subcategoria);

    void delete(SubcategoriaId id);

    void update(SubcategoriaId id, Subcategoria subcategoria);

    // 1. Method for microservice-subcategories
    List<Subcategoria> findSubcategorieByCategoryId(Integer idCategoria);

    // 1. Method for microservice-lines
    LineasBySubcategoriesResponse findLinesBySubcategory(Integer idCategoria, Integer idSubcategoria);
}
