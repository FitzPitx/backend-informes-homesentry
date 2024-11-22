package com.microservice.acumulado.microservice_acumulado.repository;

import java.util.List;

public interface AccumulatedCategoryRepositoryCustom {
    List<Object[]> obtainSummaryByBranch(Integer branchId, Integer currentYear);

}
