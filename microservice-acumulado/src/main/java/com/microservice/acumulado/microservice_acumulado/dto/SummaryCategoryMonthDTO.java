package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryCategoryMonthDTO {
    private Integer codigoCategoria;
    private String nombreCategoria;
    private String nombreMes;
    private Long totalCantidad;
    private Double totalValor;
    private Double totalValorIva;
    private Double totalCosto;
}
