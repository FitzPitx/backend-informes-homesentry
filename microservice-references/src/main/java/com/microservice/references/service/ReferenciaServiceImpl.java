package com.microservice.references.service;

import com.microservice.references.entities.Referencia;
import com.microservice.references.repository.IReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ReferenciaServiceImpl implements IReferenciaService {

    @Autowired
    private IReferenciaRepository referenciaRepository;


    @Override
    public Page<Referencia> findAll(Pageable pageable) {
        return referenciaRepository.findAll(pageable);
    }

    @Override
    public Referencia findById(Long idLinea) {
        return referenciaRepository.findById(idLinea).orElseThrow();
    }

    @Override
    public void save(Referencia referencia) {
        referenciaRepository.save(referencia);
    }

    @Override
    public void delete(Long idLinea) {
        referenciaRepository.deleteById(idLinea);
    }

    @Override
    public void update(Long idLinea, Referencia referencia) {
        Referencia referenciaToUpdate = referenciaRepository.findById(idLinea).orElseThrow();
        referenciaRepository.save(referenciaToUpdate);
    }

    @Override
    public List<Referencia> findReferencesByLineId(Long idCategoria, Long idSubcategoria, Long idLinea) {
        return referenciaRepository.findById_CategoriaMriAndId_SubcategoriaMriAndId_LineaMri(idCategoria, idSubcategoria, idLinea);
    }


}
