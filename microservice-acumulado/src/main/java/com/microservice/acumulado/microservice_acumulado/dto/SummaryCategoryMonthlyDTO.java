package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryCategoryMonthlyDTO {
    private Integer codigoCategoria;
    private String nombreCategoria;
    private Map<String, MonthlyData> meses;
}
