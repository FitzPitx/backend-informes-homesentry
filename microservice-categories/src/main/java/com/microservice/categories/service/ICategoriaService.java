package com.microservice.categories.service;

import com.microservice.categories.entities.Categoria;
import jdk.jfr.Category;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> getAllCategory();
    Categoria getCategoryById(Integer id);
    Categoria saveCategory(Categoria supplier);
    void deleteCategory(Integer id);
    void updateCategory(Integer id, Categoria categoria);

}
