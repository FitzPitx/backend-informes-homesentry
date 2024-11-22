package com.microservice.acumulado.microservice_acumulado.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccumulatedCategoryRepositoryImpl implements AccumulatedCategoryRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> obtainSummaryByBranch(Integer branchId, Integer currentYear) {

        int previousYear = currentYear - 1;

        String currentYearTable = "acum_cat_" + currentYear.toString().substring(2);
        String previousYearTable = "acum_cat_" + Integer.toString(previousYear).substring(2);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("    c.COD_CEG AS CodigoCategoria, ")
                .append("    c.NOMCAT_CEG AS NombreCategoria, ")
                .append("    m.Nombre AS Mes, ")
                .append("    s.DESSUC_SUC AS NombreSucursal, ")
                .append("    ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END), 0) AS VentaActual, ")
                .append("    ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.costo ELSE 0 END), 0) AS CostoActual, ")
                .append("    ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN (aCurrent.valor - aCurrent.valor_iva) - aCurrent.costo ELSE 0 END), 0) AS UtilidadActual, ")
                .append("    CASE ")
                .append("        WHEN ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END), 0) = 0 THEN 0 ")
                .append("        ELSE ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN (aCurrent.valor - aCurrent.valor_iva) - aCurrent.costo ELSE 0 END), 0) / ")
                .append("             ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END), 0) * 100 ")
                .append("    END AS MargenActual, ")
                .append("    ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) AS VentaAnterior, ")
                .append("    ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.costo ELSE 0 END), 0) AS CostoAnterior, ")
                .append("    ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN (aPrevious.valor - aPrevious.valor_iva) - aPrevious.costo ELSE 0 END), 0) AS UtilidadAnterior, ")
                .append("    CASE ")
                .append("        WHEN ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) = 0 THEN 0 ")
                .append("        ELSE ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN (aPrevious.valor - aPrevious.valor_iva) - aPrevious.costo ELSE 0 END), 0) / ")
                .append("             ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) * 100 ")
                .append("    END AS MargenAnterior, ")
                .append("    ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END), 0) - ")
                .append("    ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) AS DiferenciaVentas, ")
                .append("    CASE ")
                .append("        WHEN ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) = 0 THEN 0 ")
                .append("        ELSE (ISNULL(SUM(CASE WHEN aCurrent.operacion_actividad > 50 THEN aCurrent.valor - aCurrent.valor_iva ELSE 0 END), 0) - ")
                .append("              ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0)) / ")
                .append("             ISNULL(SUM(CASE WHEN aPrevious.operacion_actividad > 50 THEN aPrevious.valor - aPrevious.valor_iva ELSE 0 END), 0) * 100 ")
                .append("    END AS VariacionVentas ")
                .append("FROM ")
                .append("    " + currentYearTable + " aCurrent ")
                .append("LEFT JOIN ")
                .append("    " + previousYearTable + " aPrevious ON aCurrent.codigo = aPrevious.codigo AND aCurrent.sucursal = aPrevious.sucursal AND aCurrent.fecha_mes = aPrevious.fecha_mes ")
                .append("INNER JOIN ")
                .append("    categorias c ON aCurrent.codigo = c.COD_CEG ")
                .append("INNER JOIN ")
                .append("    sucursales s ON aCurrent.sucursal = s.CODSUC_SUC ")
                .append("INNER JOIN ")
                .append("    Meses m ON aCurrent.fecha_mes = m.Numero ")
                .append("WHERE ")
                .append("    s.codact_suc = 1 AND s.tipsuc_suc = 1 ");

        // Filtro dinámico para la sucursal
        if (branchId != null) {
            sql.append(" AND aCurrent.sucursal = :sucursal ");
        }

        sql.append("GROUP BY ")
                .append("    c.COD_CEG, c.NOMCAT_CEG, m.Nombre, s.DESSUC_SUC, m.Numero, s.CODSUC_SUC ")
                .append("ORDER BY ")
                .append("    c.COD_CEG, m.Numero, s.CODSUC_SUC");

        Query query = entityManager.createNativeQuery(sql.toString());

        if (branchId != null) {
            query.setParameter("sucursal", branchId);
        }

        return query.getResultList();
    }
}
