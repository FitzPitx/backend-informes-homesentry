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
    public Referencia findById(String id) {
        return referenciaRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Referencia referencia) {
        referenciaRepository.save(referencia);
    }

    @Override
    public void delete(String id) {
        referenciaRepository.deleteById(id);
    }

    @Override
    public void update(String id, Referencia referencia) {
        Referencia referenciaToUpdate = referenciaRepository.findById(id).orElseThrow();
        referenciaRepository.save(referenciaToUpdate);
    }
}
