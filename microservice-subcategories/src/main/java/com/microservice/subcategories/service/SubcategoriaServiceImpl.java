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
        return subcategoriaRepository.findAll();
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
        subcategoriaRepository.save(subcategoriaToUpdate);
    }

    @Override
    public List<Subcategoria> findSubcategorieByCategoryId(Integer idCategoria) {
        return subcategoriaRepository.findById_IdCategoria(idCategoria);
    }
}
