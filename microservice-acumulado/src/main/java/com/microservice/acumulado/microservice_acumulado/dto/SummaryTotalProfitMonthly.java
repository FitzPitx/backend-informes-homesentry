package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryTotalProfitMonthly {
    private String month;
    private Double totalProfitCurrentYear;
    private Double totalProfitLastYear;
}
