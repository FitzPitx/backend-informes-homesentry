package com.microservice.lines.persistence;

import com.microservice.lines.entities.Linea;
import com.microservice.lines.entities.LineaId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineaRepository extends CrudRepository<Linea, LineaId> {
    List<Linea> findById_CodcatLeaAndId_CodsubLea(Integer idCategoria,Integer idSubcategoria);
}
