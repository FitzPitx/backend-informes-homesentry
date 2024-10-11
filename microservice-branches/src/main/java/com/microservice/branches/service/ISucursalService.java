package com.microservice.branches.service;

import com.microservice.branches.entities.Sucursal;

import java.util.List;

public interface ISucursalService {

    List<Sucursal> findAll();

    Sucursal findById(Short id);

    void save(Sucursal sucursal);

    void delete(Short id);
}
