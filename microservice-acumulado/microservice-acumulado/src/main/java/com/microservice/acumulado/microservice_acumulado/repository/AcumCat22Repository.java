package com.microservice.acumulado.microservice_acumulado.repository;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.entities.AcumCat22;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcumCat22Repository extends CrudRepository<AcumCat22, Integer> {
    @Query(value = "SELECT codigo, sucursal, " +
            "SUM(cantidad) AS totalCantidad, " +
            "SUM(valor) AS totalValor, " +
            "SUM(valor_iva) AS totalValorIva, " +
            "SUM(costo) AS totalCosto " +
            "FROM acum_cat_22 " +
            "GROUP BY codigo, sucursal", nativeQuery = true)
    List<CategoriaSucursalResumen> findCategoriaSucursalResumen();
}
