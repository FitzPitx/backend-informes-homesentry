package com.microservice.acumulado.microservice_acumulado.repository;

import com.microservice.acumulado.microservice_acumulado.entities.AcumCat22;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccumulatedCategoryRepository extends CrudRepository<AcumCat22, Integer>, AccumulatedCategoryRepositoryCustom {

    // Categories summary query
    @Query(value = "SELECT c.COD_CEG AS codigoCategoria, " +
            "c.NOMCAT_CEG AS nombreCategoria, " +
            "SUM(a.cantidad) AS totalCantidad, " +
            "SUM(a.valor) AS totalValor, " +
            "SUM(a.valor_iva) AS totalValorIva, " +
            "SUM(a.costo) AS totalCosto " +
            "FROM acum_cat_22 a " +
            "INNER JOIN categorias c ON a.codigo = c.COD_CEG " +
            "GROUP BY c.COD_CEG, c.NOMCAT_CEG " +
            "ORDER BY c.COD_CEG", nativeQuery = true)
    List<Object[]> findSummaryByCategory();


    // Categories summary by month query
    @Query(value = "SELECT c.COD_CEG AS codigoCategoria, " +
            "c.NOMCAT_CEG AS nombreCategoria, " +
            "m.Nombre AS nombreMes, " +
            "SUM(a.cantidad) AS totalCantidad, " +
            "SUM(a.valor) AS totalValor, " +
            "SUM(a.valor_iva) AS totalValorIva, " +
            "SUM(a.costo) AS totalCosto " +
            "FROM acum_cat_22 a " +
            "INNER JOIN categorias c ON a.codigo = c.COD_CEG " +
            "INNER JOIN Meses m ON a.fecha_mes = m.Numero " +
            "GROUP BY c.COD_CEG, c.NOMCAT_CEG, m.Nombre, m.Numero " +
            "ORDER BY m.Numero, c.COD_CEG", nativeQuery = true)
    List<Object[]> findSummaryByCategoryByMonth();


    // Query to obtain all percentages according to the previous year and
    // the current one in this case 2023 current and 2022 previous


}
