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
    public List<Object[]> obtainSummaryByBranch(Integer currentYear, Integer branchId ) {

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
                .append("ORDER BY va.codigo, va.mes;");

        Query query = entityManager.createNativeQuery(sql.toString());

        if (branchId != null) {
            query.setParameter("sucursal", branchId);
        }

        return query.getResultList();
    }
}
