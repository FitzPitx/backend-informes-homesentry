package com.microservice.subcategories.persistence;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubcategoriaRepository extends CrudRepository<Subcategoria, SubcategoriaId> {
}
