package com.microservice.acumulado.microservice_acumulado.dto;

public interface CategoriaSucursalResumen {
    Integer getCodigo();
    Integer getSucursal();
    Double getTotalCantidad();
    Double getTotalValor();
    Double getTotalValorIva();
    Double getTotalCosto();
}
