package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryTotalCategory {
    private Integer codigoCategoria;
    private String nombreCategoria;
    private Double totalVentasActual;
    private Double totalVentasAnterior;
}
