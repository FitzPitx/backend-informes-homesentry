package com.microservice.references.controller;

import com.microservice.references.entities.Referencia;
import com.microservice.references.service.IReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/referencias")
public class ReferenciaController{

    @Autowired
    private IReferenciaService referenciaService;

    @GetMapping("/all")
    public ResponseEntity<Page<Referencia>> findAll(Pageable pageable){
        Page<Referencia> referenciaPage = referenciaService.findAll(pageable);
        return ResponseEntity.ok(referenciaPage);
    }
}
