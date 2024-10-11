package com.microservice.branches.persistence;

import com.microservice.branches.entities.Sucursal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends CrudRepository<Sucursal, Short> {

}
