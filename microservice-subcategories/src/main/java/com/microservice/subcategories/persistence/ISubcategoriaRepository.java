package com.microservice.subcategories.persistence;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubcategoriaRepository extends JpaRepository<Subcategoria, SubcategoriaId> {
        List<Subcategoria> findById_IdCategoria(Integer idCategoria);

        List<Subcategoria> findById_IdCategoriaAndId_IdSubcategoria(Integer idCategoria, Integer idSubcategoria);
}
