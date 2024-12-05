package com.microservice.acumulado.microservice_acumulado.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccumulatedCategoryRepositoryImpl implements AccumulatedCategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List obtainSummaryByBranch(Integer currentYear, Integer branchId ) {

        int previousYear = currentYear - 1;

        String currentYearTable = "acum_cat_" + currentYear.toString().substring(2);
        String previousYearTable = "acum_cat_" + Integer.toString(previousYear).substring(2);

        StringBuilder sql = new StringBuilder();
        sql.append("WITH ventas_actuales AS ( ")
                .append("    SELECT ")
                .append("        aCurrent.codigo AS codigo, ")
                .append("        c.NOMCAT_CEG AS nombre, ")
                .append("        aCurrent.fecha_mes AS mes, ")
                .append("        SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END) AS venta_actual, ")
                .append("        SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.costo ELSE 0 END) AS costo_actual ")
                .append("    FROM ")
                .append("        " + currentYearTable + " aCurrent ")
                .append("    INNER JOIN categorias c ON aCurrent.codigo = c.COD_CEG ")
                .append("    INNER JOIN sucursales s ON aCurrent.sucursal = s.CODSUC_SUC ")
                .append("    WHERE ")
                .append("        s.CODACT_SUC = 1 AND s.TIPSUC_SUC = 1 ")
                .append("        AND aCurrent.operacion_actividad > 50 ")
                .append("        AND aCurrent.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");

        // Filtro dinámico para sucursal
        if (branchId != null) {
            sql.append(" AND aCurrent.sucursal = :sucursal ");
        }

        sql.append("    GROUP BY aCurrent.codigo, c.NOMCAT_CEG, aCurrent.fecha_mes ")
                .append("), ")
                .append("ventas_anteriores AS ( ")
                .append("    SELECT ")
                .append("        aPrevious.codigo AS codigo, ")
                .append("        aPrevious.fecha_mes AS mes, ")
                .append("        SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END) AS venta_anterior, ")
                .append("        SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.costo ELSE 0 END) AS costo_anterior ")
                .append("    FROM ")
                .append("        " + previousYearTable + " aPrevious ")
                .append("    INNER JOIN sucursales s ON aPrevious.sucursal = s.CODSUC_SUC ")
                .append("    WHERE ")
                .append("        s.CODACT_SUC = 1 AND s.TIPSUC_SUC = 1 ")
                .append("        AND aPrevious.operacion_actividad > 50 ")
                .append("        AND aPrevious.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");

        // Filtro dinámico para sucursal
        if (branchId != null) {
            sql.append(" AND aPrevious.sucursal = :sucursal ");
        }

        sql.append("    GROUP BY aPrevious.codigo, aPrevious.fecha_mes ")
                .append(") ")
                .append("SELECT ")
                .append("    va.codigo AS codigo, ")
                .append("    va.nombre AS nombre, ")
                .append("    m.Nombre AS mes, ")
                .append("    va.venta_actual AS venta_actual, ")
                .append("    (va.venta_actual - va.costo_actual) AS utilidad_actual, ")
                .append("    ((va.venta_actual - va.costo_actual) / NULLIF(va.venta_actual, 0)) * 100 AS margen_actual, ")
                .append("    v_ant.venta_anterior AS venta_anterior, ")
                .append("    (v_ant.venta_anterior - v_ant.costo_anterior) AS utilidad_anterior, ")
                .append("    ((v_ant.venta_anterior - v_ant.costo_anterior) / NULLIF(v_ant.venta_anterior, 0)) * 100 AS margen_anterior, ")
                .append("    (va.venta_actual - v_ant.venta_anterior) AS diferencia_ventas, ")
                .append("    (va.venta_actual - va.costo_actual) - (v_ant.venta_anterior - v_ant.costo_anterior) AS diferencia_utilidad, ")
                .append("    ((va.venta_actual - v_ant.venta_anterior) / NULLIF(v_ant.venta_anterior, 0)) * 100 AS variacion_ventas ")
                .append("FROM ")
                .append("    ventas_actuales va ")
                .append("LEFT JOIN ventas_anteriores v_ant ON va.codigo = v_ant.codigo AND va.mes = v_ant.mes ")
                .append("INNER JOIN meses m ON va.mes = m.Numero ")
                .append("ORDER BY va.mes, va.codigo;");

        Query query = entityManager.createNativeQuery(sql.toString());

        if (branchId != null) {
            query.setParameter("sucursal", branchId);
        }

        return query.getResultList();
    }

    @Override
    public List getTotalSummaryGraph(Integer year) {

        // Calcular los años actual y anterior
        String currentYearTable = "acum_cat_" + String.valueOf(year).substring(2);
        String previousYearTable = "acum_cat_" + String.valueOf(year - 1).substring(2);

        String sql = "WITH ventas_actuales AS (" +
                "    SELECT " +
                "        m.Numero AS numeroMes, " +
                "        m.Nombre AS mes, " +
                "        SUM(CASE " +
                "            WHEN a23.operacion_actividad > 50 THEN a23.valor - a23.valor_iva " +
                "            ELSE 0 END) AS totalVentasAñoActual " +
                "    FROM " +
                "        " + currentYearTable + " a23 " + // Tabla dinámica
                "    JOIN meses m ON a23.fecha_mes = m.Numero " +
                "    JOIN sucursales s ON a23.sucursal = s.CODSUC_SUC " +
                "    WHERE " +
                "        s.CODACT_SUC = 1 " +
                "        AND s.TIPSUC_SUC = 1 " +
                "        AND a23.operacion_actividad > 50 " +
                "        AND a23.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) " +
                "    GROUP BY " +
                "        m.Numero, m.Nombre " +
                "), " +
                "ventas_anteriores AS (" +
                "    SELECT " +
                "        m.Numero AS numeroMes, " +
                "        m.Nombre AS mes, " +
                "        SUM(CASE " +
                "            WHEN a22.operacion_actividad > 50 THEN a22.valor - a22.valor_iva " +
                "            ELSE 0 END) AS totalVentasAñoAnterior " +
                "    FROM " +
                "        " + previousYearTable + " a22 " + // Tabla dinámica
                "    JOIN meses m ON a22.fecha_mes = m.Numero " +
                "    JOIN sucursales s ON a22.sucursal = s.CODSUC_SUC " +
                "    WHERE " +
                "        s.CODACT_SUC = 1 " +
                "        AND s.TIPSUC_SUC = 1 " +
                "        AND a22.operacion_actividad > 50 " +
                "        AND a22.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) " +
                "    GROUP BY " +
                "        m.Numero, m.Nombre " +
                ") " +
                "SELECT " +
                "    va.mes AS Mes, " +
                "    ISNULL(va.totalVentasAñoActual, 0) AS TotalVentasAñoActual, " +
                "    ISNULL(v_ant.totalVentasAñoAnterior, 0) AS TotalVentasAñoAnterior " +
                "FROM " +
                "    ventas_actuales va " +
                "LEFT JOIN ventas_anteriores v_ant ON va.numeroMes = v_ant.numeroMes " +
                "ORDER BY " +
                "    va.numeroMes ASC;";

        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public List getTotalSummaryGraphByCategory(Integer year) {
        // Calcular los nombres de las tablas para el año actual y anterior
        String currentYearTable = "acum_cat_" + String.valueOf(year).substring(2);
        String previousYearTable = "acum_cat_" + String.valueOf(year - 1).substring(2);

        String sql = "WITH ventas_actuales AS (" +
                "    SELECT " +
                "        c.COD_CEG AS codigoCategoria, " +
                "        c.NOMCAT_CEG AS nombreCategoria, " +
                "        SUM(CASE " +
                "            WHEN a23.operacion_actividad > 50 THEN a23.valor - a23.valor_iva " +
                "            ELSE 0 END) AS totalVentasAñoActual " +
                "    FROM " +
                "        " + currentYearTable + " a23 " + // Tabla dinámica
                "    JOIN categorias c ON a23.codigo = c.COD_CEG " +
                "    JOIN sucursales s ON a23.sucursal = s.CODSUC_SUC " +
                "    WHERE " +
                "        s.CODACT_SUC = 1 " +
                "        AND s.TIPSUC_SUC = 1 " +
                "        AND a23.operacion_actividad > 50 " +
                "        AND a23.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) " +
                "    GROUP BY " +
                "        c.COD_CEG, c.NOMCAT_CEG " +
                "), " +
                "ventas_anteriores AS (" +
                "    SELECT " +
                "        c.COD_CEG AS codigoCategoria, " +
                "        c.NOMCAT_CEG AS nombreCategoria, " +
                "        SUM(CASE " +
                "            WHEN a22.operacion_actividad > 50 THEN a22.valor - a22.valor_iva " +
                "            ELSE 0 END) AS totalVentasAñoAnterior " +
                "    FROM " +
                "        " + previousYearTable + " a22 " + // Tabla dinámica
                "    JOIN categorias c ON a22.codigo = c.COD_CEG " +
                "    JOIN sucursales s ON a22.sucursal = s.CODSUC_SUC " +
                "    WHERE " +
                "        s.CODACT_SUC = 1 " +
                "        AND s.TIPSUC_SUC = 1 " +
                "        AND a22.operacion_actividad > 50 " +
                "        AND a22.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) " +
                "    GROUP BY " +
                "        c.COD_CEG, c.NOMCAT_CEG " +
                ") " +
                "SELECT " +
                "    va.codigoCategoria AS CodigoCategoria, " +
                "    va.nombreCategoria AS NombreCategoria, " +
                "    ISNULL(va.totalVentasAñoActual, 0) AS TotalVentasAñoActual, " +
                "    ISNULL(v_ant.totalVentasAñoAnterior, 0) AS TotalVentasAñoAnterior " +
                "FROM " +
                "    ventas_actuales va " +
                "LEFT JOIN ventas_anteriores v_ant ON va.codigoCategoria = v_ant.codigoCategoria " +
                "ORDER BY " +
                "    va.codigoCategoria;";

        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

}
