package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SummaryTotalMonthly {
    private String month;
    private Double totalSaleCurrentYear;
    private Double totalSaleLastYear;
}
