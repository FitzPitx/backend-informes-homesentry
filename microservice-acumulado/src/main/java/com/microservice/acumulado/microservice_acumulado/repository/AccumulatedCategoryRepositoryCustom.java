package com.microservice.acumulado.microservice_acumulado.repository;

import java.util.List;

public interface AccumulatedCategoryRepositoryCustom {
    List<Object[]> obtainSummaryByBranch(Integer currentYear, Integer branchId);
    List<Object[]> getTotalSummaryGraph(Integer year, Integer sucursal);
    List<Object[]> getTotalSummaryGraphByCategory(Integer year, Integer sucursal);
    List<Object[]> getTotalProfitComparisonByMonth(Integer year, Integer sucursal);
}
