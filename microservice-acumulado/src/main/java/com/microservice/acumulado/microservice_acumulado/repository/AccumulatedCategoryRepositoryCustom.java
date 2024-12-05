package com.microservice.acumulado.microservice_acumulado.repository;

import java.util.List;

public interface AccumulatedCategoryRepositoryCustom {
    List<Object[]> obtainSummaryByBranch(Integer currentYear, Integer branchId);
    List<Object[]> getTotalSummaryGraph(Integer year);
    List<Object[]> getTotalSummaryGraphByCategory(Integer year);
}
