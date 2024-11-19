package com.microservice.acumulado.microservice_acumulado.repository;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.entities.AcumCat22;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcumCat22Repository extends CrudRepository<AcumCat22, Integer> {
    @Query(value = "SELECT codigo, sucursal, fecha_mes, " +
            "SUM(cantidad) AS totalCantidad, " +
            "SUM(valor) AS totalValor, " +
            "SUM(valor_iva) AS totalValorIva, " +
            "SUM(costo) AS totalCosto " +
            "FROM acum_cat_22 " +
            "GROUP BY codigo, sucursal, fecha_mes", nativeQuery = true)
    List<CategoriaSucursalResumen> findCategoriaSucursalResumen();

    // Query para las sucursales con sus totales
    @Query(value = "SELECT s.CODSUC_SUC AS codigoSucursal, " +
            "s.DESSUC_SUC AS nombreSucursal, " +
            "SUM(a.cantidad) AS totalCantidad, " +
            "SUM(a.valor) AS totalValor, " +
            "SUM(a.valor_iva) AS totalValorIva, " +
            "SUM(a.costo) AS totalCosto " +
            "FROM acum_cat_22 a " +
            "INNER JOIN sucursales s ON a.sucursal = s.CODSUC_SUC " +
            "GROUP BY s.CODSUC_SUC, s.DESSUC_SUC " +
            "ORDER BY s.CODSUC_SUC", nativeQuery = true)
    List<Object[]> findResumenPorSucursal();

    // Query para las categorías con sus totales
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
    List<Object[]> findResumenPorCategoria();

    // Query para obtener el resumen por mes
    @Query(value = "SELECT s.CODSUC_SUC AS codigoSucursal, " +
            "s.DESSUC_SUC AS nombreSucursal, " +
            "a.fecha_mes AS mes, " +
            "SUM(a.cantidad) AS totalCantidad, " +
            "SUM(a.valor) AS totalValor, " +
            "SUM(a.valor_iva) AS totalValorIva, " +
            "SUM(a.costo) AS totalCosto " +
            "FROM acum_cat_22 a " +
            "INNER JOIN sucursales s ON a.sucursal = s.CODSUC_SUC " +
            "GROUP BY s.CODSUC_SUC, s.DESSUC_SUC, a.fecha_mes " +
            "ORDER BY a.fecha_mes, s.CODSUC_SUC", nativeQuery = true)
    List<Object[]> findResumenPorSucursalConMes();

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
    List<Object[]> findResumenPorCategoriaConMes();
}
