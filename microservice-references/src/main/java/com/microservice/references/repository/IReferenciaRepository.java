package com.microservice.references.repository;

import com.microservice.references.entities.Referencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReferenciaRepository extends JpaRepository<Referencia, String> {
}
