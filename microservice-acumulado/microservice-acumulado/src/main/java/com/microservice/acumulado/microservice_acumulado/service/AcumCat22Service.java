package com.microservice.acumulado.microservice_acumulado.service;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenCategoriaDTO;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenCategoriaMesDTO;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenSucursalDTO;
import com.microservice.acumulado.microservice_acumulado.repository.AcumCat22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcumCat22Service {

    private final AcumCat22Repository acumCat22Repository;

    @Autowired
    public AcumCat22Service(AcumCat22Repository acumCat22Repository) {
        this.acumCat22Repository = acumCat22Repository;
    }

    public List<CategoriaSucursalResumen> getCategoriaSucursalResumen() {
        return acumCat22Repository.findCategoriaSucursalResumen();
    }

    // Método para obtener el resumen por sucursal
    public List<ResumenSucursalDTO> getResumenPorSucursal() {
        List<Object[]> results = acumCat22Repository.findResumenPorSucursal();
        List<ResumenSucursalDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            ResumenSucursalDTO dto = new ResumenSucursalDTO();
            dto.setCodigoSucursa((Integer) result[0]);
            dto.setNombreSucursal((String) result[1]);
            dto.setTotalCantidad(((Number) result[2]).longValue());
            dto.setTotalValor(((Number) result[3]).doubleValue());
            dto.setTotalValorIva(((Number) result[4]).doubleValue());
            dto.setTotalCosto(((Number) result[5]).doubleValue());
            resumenList.add(dto);
        }

        return resumenList;
    }

    // Método para obtener el resumen por categoría
    public List<ResumenCategoriaDTO> getResumenPorCategoria() {
        List<Object[]> results = acumCat22Repository.findResumenPorCategoria();
        List<ResumenCategoriaDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            ResumenCategoriaDTO dto = new ResumenCategoriaDTO();
            dto.setCodigoCategoria((Integer) result[0]);
            dto.setNombreCategoria((String) result[1]);
            dto.setTotalCantidad(((Number) result[2]).longValue());
            dto.setTotalValor(((Number) result[3]).doubleValue());
            dto.setTotalValorIva(((Number) result[4]).doubleValue());
            dto.setTotalCosto(((Number) result[5]).doubleValue());
            resumenList.add(dto);
        }

        return resumenList;
    }

    // Método para obtener el resumen por mes
    public List<ResumenSucursalDTO> getResumenPorSucursalConMes() {
        List<Object[]> results = acumCat22Repository.findResumenPorSucursalConMes();
        List<ResumenSucursalDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            ResumenSucursalDTO dto = new ResumenSucursalDTO();
            dto.setCodigoSucursa((Integer) result[0]);
            dto.setNombreSucursal((String) result[1]);
            dto.setTotalCantidad(((Number) result[3]).longValue());
            dto.setTotalValor(((Number) result[4]).doubleValue());
            dto.setTotalValorIva(((Number) result[5]).doubleValue());
            dto.setTotalCosto(((Number) result[6]).doubleValue());
            resumenList.add(dto);
        }

        return resumenList;
    }

    // Método para obtener el resumen por mes de categorías
    public List<ResumenCategoriaMesDTO> getResumenPorCategoriaConMes() {
        List<Object[]> results = acumCat22Repository.findResumenPorCategoriaConMes();
        List<ResumenCategoriaMesDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            ResumenCategoriaMesDTO dto = new ResumenCategoriaMesDTO();
            dto.setCodigoCategoria((Integer) result[0]);
            dto.setNombreCategoria((String) result[1]);
            dto.setMes((Integer) result[2]);
            dto.setTotalCantidad(((Number) result[3]).longValue());
            dto.setTotalValor(((Number) result[4]).doubleValue());
            dto.setTotalValorIva(((Number) result[5]).doubleValue());
            dto.setTotalCosto(((Number) result[6]).doubleValue());
            resumenList.add(dto);
        }

        return resumenList;
    }
}
