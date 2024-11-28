package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyData {
    private Double ventaActual;
    private Double utilidadActual;
    private Double margenActual;
    private Double ventaAnterior;
    private Double utilidadAnterior;
    private Double margenAnterior;
    private Double diferenciaVentas;
    private Double diferenciaUtilidad;
    private Double variacionVentas;
}
