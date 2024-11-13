package com.microservice.acumulado.microservice_acumulado.service;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.repository.AcumCat22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
