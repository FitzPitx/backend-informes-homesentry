package com.microservice.acumulado.microservice_acumulado.dto;

public interface CategoriaSucursalResumen {
    Integer getCodigo();
    Integer getSucursal();
    Integer getFechaMes();
    Double getTotalCantidad();
    Double getTotalValor();
    Double getTotalValorIva();
    Double getTotalCosto();
}
