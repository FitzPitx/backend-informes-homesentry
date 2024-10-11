package com.microservice.branches.service;

import com.microservice.branches.entities.Sucursal;
import com.microservice.branches.persistence.ISucursalRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements ISucursalService{

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return (List<Sucursal>) sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Short id) {
        return sucursalRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public void delete(Short id) {
        sucursalRepository.deleteById(id);
    }
}
