package com.microservice.lines.service;

import com.microservice.lines.entities.Linea;
import com.microservice.lines.entities.LineaId;
import com.microservice.lines.persistence.ILineaRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaServiceImpl implements ILineaService{

    @Autowired
    private ILineaRepository lineaRepository;

    @Override
    public List<Linea> findAll() {
        return (List<Linea>) lineaRepository.findAll();
    }

    @Override
    public Linea findById(LineaId id) {
        return lineaRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Linea linea) {
        lineaRepository.save(linea);

    }
    @Override
    public void delete(LineaId id) {
        lineaRepository.deleteById(id);
    }

    @Override
    public List<Linea> findLinesBySubcategoryId(Integer idCategoria, Integer idSubcategoria) {
        return lineaRepository.findById_CodcatLeaAndId_CodsubLea(idCategoria, idSubcategoria);
    }


}
