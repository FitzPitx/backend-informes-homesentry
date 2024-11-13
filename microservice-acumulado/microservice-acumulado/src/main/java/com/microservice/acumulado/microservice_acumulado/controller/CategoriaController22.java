package com.microservice.acumulado.microservice_acumulado.controller;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.service.AcumCat22Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/categorias22")
public class CategoriaController22 {


    private final AcumCat22Service acumCat22Service;

    @Autowired
    public CategoriaController22(AcumCat22Service acumCat22Service) {
        this.acumCat22Service = acumCat22Service;
    }

    @GetMapping("/categorias-sucursal-resumen")
    public List<CategoriaSucursalResumen> getResumen() {
        return acumCat22Service.getCategoriaSucursalResumen();
    }
}
