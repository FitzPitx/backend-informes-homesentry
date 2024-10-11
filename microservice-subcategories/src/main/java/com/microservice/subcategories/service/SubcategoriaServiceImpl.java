package com.microservice.subcategories.service;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import com.microservice.subcategories.persistence.ISubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoriaServiceImpl implements ISubcategoriaService{

    @Autowired
    private ISubcategoriaRepository subcategoriaRepository;

    @Override
    public List<Subcategoria> findAll() {
        return (List<Subcategoria>) subcategoriaRepository.findAll();
    }

    @Override
    public Subcategoria findById(SubcategoriaId id) {
        return subcategoriaRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Subcategoria subcategoria) {
        subcategoriaRepository.save(subcategoria);
    }

    @Override
    public void delete(SubcategoriaId id) {
        subcategoriaRepository.deleteById(id);
    }

    @Override
    public void update(SubcategoriaId id, Subcategoria subcategoria) {
        Subcategoria subcategoriaToUpdate = subcategoriaRepository.findById(id).orElseThrow();
        /**
         *  Dejar estos metodos cuando se quieran implementar en un futuro
         */
//        subcategoriaToUpdate.setNomsubcatCeg(subcategoria.getNomsubcatCeg());
//        subcategoriaToUpdate.setEstadoCeg(subcategoria.getEstadoCeg());
//        subcategoriaToUpdate.setUsuarioCreo(subcategoria.getUsuarioCreo());
//        subcategoriaToUpdate.setFechaCreacion(subcategoria.getFechaCreacion());
//        subcategoriaToUpdate.setCodcatCeg(subcategoria.getCodcatCeg());
//        subcategoriaToUpdate.setExcluidoCeg(subcategoria.getExcluidoCeg());
        subcategoriaRepository.save(subcategoriaToUpdate);
    }

    @Override
    public List<Subcategoria> findSubcategoriaById(SubcategoriaId codcatSce) {
        return subcategoriaRepository.findSubcategoriaById(codcatSce);
    }
}
