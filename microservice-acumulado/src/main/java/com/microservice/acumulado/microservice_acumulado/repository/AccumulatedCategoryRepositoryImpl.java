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
    public List getTotalSummaryGraph(Integer year, Integer sucursal) {

        // Calcular los años actual y anterior
        String currentYearTable = "acum_cat_" + String.valueOf(year).substring(2);
        String previousYearTable = "acum_cat_" + String.valueOf(year - 1).substring(2);

        StringBuilder sql = new StringBuilder();

        sql.append("WITH ventas_actuales AS (");
        sql.append("    SELECT ");
        sql.append("        m.Numero AS numeroMes, ");
        sql.append("        m.Nombre AS mes, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ");
        sql.append("            ELSE 0 END) AS totalVentasAñoActual ");
        sql.append("    FROM ");
        sql.append("        ").append(currentYearTable).append(" aCurrent ");
        sql.append("    JOIN meses m ON aCurrent.fecha_mes = m.Numero ");
        sql.append("    JOIN sucursales s ON aCurrent.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aCurrent.operacion_actividad > 50 ");
        sql.append("        AND aCurrent.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aCurrent.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        m.Numero, m.Nombre ");
        sql.append("), ");
        sql.append("ventas_anteriores AS (");
        sql.append("    SELECT ");
        sql.append("        m.Numero AS numeroMes, ");
        sql.append("        m.Nombre AS mes, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ");
        sql.append("            ELSE 0 END) AS totalVentasAñoAnterior ");
        sql.append("    FROM ");
        sql.append("        ").append(previousYearTable).append(" aPrevious ");
        sql.append("    JOIN meses m ON aPrevious.fecha_mes = m.Numero ");
        sql.append("    JOIN sucursales s ON aPrevious.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aPrevious.operacion_actividad > 50 ");
        sql.append("        AND aPrevious.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aPrevious.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        m.Numero, m.Nombre ");
        sql.append(") ");
        sql.append("SELECT ");
        sql.append("    va.mes AS Mes, ");
        sql.append("    ISNULL(va.totalVentasAñoActual, 0) AS TotalVentasAñoActual, ");
        sql.append("    ISNULL(v_ant.totalVentasAñoAnterior, 0) AS TotalVentasAñoAnterior ");
        sql.append("FROM ");
        sql.append("    ventas_actuales va ");
        sql.append("LEFT JOIN ventas_anteriores v_ant ON va.numeroMes = v_ant.numeroMes ");
        sql.append("ORDER BY ");
        sql.append("    va.numeroMes ASC;");

        Query query = entityManager.createNativeQuery(sql.toString());
        if (sucursal != null) {
            query.setParameter("sucursal", sucursal); // Establecer el parámetro si existe
        }
        return query.getResultList();
    }

    @Override
    public List getTotalSummaryGraphByCategory(Integer year, Integer sucursal) {
        // Calcular los nombres de las tablas para el año actual y anterior
        String currentYearTable = "acum_cat_" + String.valueOf(year).substring(2);
        String previousYearTable = "acum_cat_" + String.valueOf(year - 1).substring(2);

        StringBuilder sql = new StringBuilder();

        sql.append("WITH ventas_actuales AS (");
        sql.append("    SELECT ");
        sql.append("        c.COD_CEG AS codigoCategoria, ");
        sql.append("        c.NOMCAT_CEG AS nombreCategoria, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ");
        sql.append("            ELSE 0 END) AS totalVentasAñoActual ");
        sql.append("    FROM ");
        sql.append("        ").append(currentYearTable).append(" aCurrent ");
        sql.append("    JOIN categorias c ON aCurrent.codigo = c.COD_CEG ");
        sql.append("    JOIN sucursales s ON aCurrent.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aCurrent.operacion_actividad > 50 ");
        sql.append("        AND aCurrent.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aCurrent.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        c.COD_CEG, c.NOMCAT_CEG ");
        sql.append("), ");
        sql.append("ventas_anteriores AS (");
        sql.append("    SELECT ");
        sql.append("        c.COD_CEG AS codigoCategoria, ");
        sql.append("        c.NOMCAT_CEG AS nombreCategoria, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ");
        sql.append("            ELSE 0 END) AS totalVentasAñoAnterior ");
        sql.append("    FROM ");
        sql.append("        ").append(previousYearTable).append(" aPrevious ");
        sql.append("    JOIN categorias c ON aPrevious.codigo = c.COD_CEG ");
        sql.append("    JOIN sucursales s ON aPrevious.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aPrevious.operacion_actividad > 50 ");
        sql.append("        AND aPrevious.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aPrevious.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        c.COD_CEG, c.NOMCAT_CEG ");
        sql.append(") ");
        sql.append("SELECT ");
        sql.append("    va.codigoCategoria AS CodigoCategoria, ");
        sql.append("    va.nombreCategoria AS NombreCategoria, ");
        sql.append("    ISNULL(va.totalVentasAñoActual, 0) AS TotalVentasAñoActual, ");
        sql.append("    ISNULL(v_ant.totalVentasAñoAnterior, 0) AS TotalVentasAñoAnterior ");
        sql.append("FROM ");
        sql.append("    ventas_actuales va ");
        sql.append("LEFT JOIN ventas_anteriores v_ant ON va.codigoCategoria = v_ant.codigoCategoria ");
        sql.append("ORDER BY ");
        sql.append("    va.codigoCategoria;");

        Query query = entityManager.createNativeQuery(sql.toString());
        if (sucursal != null) {
            query.setParameter("sucursal", sucursal); // Establecer el parámetro si existe
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> getTotalProfitComparisonByMonth(Integer year, Integer sucursal) {
        // Calcular los nombres de las tablas para el año actual y anterior
        String currentYearTable = "acum_cat_" + String.valueOf(year).substring(2);
        String previousYearTable = "acum_cat_" + String.valueOf(year - 1).substring(2);

        StringBuilder sql = new StringBuilder();

        sql.append("WITH utilidad_actual AS (");
        sql.append("    SELECT ");
        sql.append("        m.Numero AS numeroMes, ");
        sql.append("        m.Nombre AS mes, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aCurrent.operacion_actividad > 50 THEN (aCurrent.valor - aCurrent.valor_iva - aCurrent.costo) ");
        sql.append("            ELSE 0 END) AS totalUtilidadAñoActual ");
        sql.append("    FROM ");
        sql.append("        ").append(currentYearTable).append(" aCurrent ");
        sql.append("    JOIN meses m ON aCurrent.fecha_mes = m.Numero ");
        sql.append("    JOIN sucursales s ON aCurrent.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aCurrent.operacion_actividad > 50 ");
        sql.append("        AND aCurrent.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aCurrent.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        m.Numero, m.Nombre ");
        sql.append("), ");
        sql.append("utilidad_anterior AS (");
        sql.append("    SELECT ");
        sql.append("        m.Numero AS numeroMes, ");
        sql.append("        m.Nombre AS mes, ");
        sql.append("        SUM(CASE ");
        sql.append("            WHEN aPrevious.operacion_actividad > 50 THEN (aPrevious.valor - aPrevious.valor_iva - aPrevious.costo) ");
        sql.append("            ELSE 0 END) AS totalUtilidadAñoAnterior ");
        sql.append("    FROM ");
        sql.append("        ").append(previousYearTable).append(" aPrevious ");
        sql.append("    JOIN meses m ON aPrevious.fecha_mes = m.Numero ");
        sql.append("    JOIN sucursales s ON aPrevious.sucursal = s.CODSUC_SUC ");
        sql.append("    WHERE ");
        sql.append("        s.CODACT_SUC = 1 ");
        sql.append("        AND s.TIPSUC_SUC = 1 ");
        sql.append("        AND aPrevious.operacion_actividad > 50 ");
        sql.append("        AND aPrevious.operacion_actividad NOT IN (52, 53, 63, 64, 11, 12, 25) ");
        if (sucursal != null) {
            sql.append("        AND aPrevious.sucursal = :sucursal "); // Parámetro dinámico
        }
        sql.append("    GROUP BY ");
        sql.append("        m.Numero, m.Nombre ");
        sql.append(") ");
        sql.append("SELECT ");
        sql.append("    ua.mes AS Mes, ");
        sql.append("    ISNULL(ua.totalUtilidadAñoActual, 0) AS TotalUtilidadAñoActual, ");
        sql.append("    ISNULL(ua_prev.totalUtilidadAñoAnterior, 0) AS TotalUtilidadAñoAnterior ");
        sql.append("FROM ");
        sql.append("    utilidad_actual ua ");
        sql.append("LEFT JOIN utilidad_anterior ua_prev ON ua.numeroMes = ua_prev.numeroMes ");
        sql.append("ORDER BY ");
        sql.append("    ua.numeroMes ASC;");

        Query query = entityManager.createNativeQuery(sql.toString());
        if (sucursal != null) {
            query.setParameter("sucursal", sucursal); // Establecer el parámetro si existe
        }
        return query.getResultList();
    }


}
