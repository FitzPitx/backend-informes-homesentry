package com.microservice.references.repository;

import com.microservice.references.entities.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReferenciaRepository extends JpaRepository<Referencia, Long> {
    public List<Referencia> findById_CategoriaMriAndId_SubcategoriaMriAndId_LineaMri(Long idCategoria, Long idSubcategoria, Long idLinea);
}
