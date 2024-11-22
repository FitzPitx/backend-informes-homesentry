package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryCategoryDTO {
    private Integer codigoCategoria;
    private String nombreCategoria;
    private Long totalCantidad;
    private Double totalValor;
    private Double totalValorIva;
    private Double totalCosto;
}
