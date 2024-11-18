package com.microservice.acumulado.microservice_acumulado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenSucursalDTO {
    private Integer codigoSucursa;
    private String nombreSucursal;
    private Long totalCantidad;
    private Double totalValor;
    private Double totalValorIva;
    private Double totalCosto;
}
