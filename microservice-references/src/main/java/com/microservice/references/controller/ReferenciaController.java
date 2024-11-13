package com.microservice.references.controller;

import com.microservice.references.entities.Referencia;
import com.microservice.references.service.IReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/referencias")
@CrossOrigin(origins = "http://localhost:4200")
public class ReferenciaController{

    @Autowired
    private IReferenciaService referenciaService;

    @GetMapping("/all")
    public ResponseEntity<Page<Referencia>> findAll(Pageable pageable){
        Page<Referencia> referenciaPage = referenciaService.findAll(pageable);
        return ResponseEntity.ok(referenciaPage);
    }

    //Endpoint para ver la lista de referencias de una linea
    @GetMapping("/search-by-line/{idCategoria}/{idSubcategoria}/{idLinea}")
    public ResponseEntity<?> findByIdLine(@PathVariable Long idCategoria, @PathVariable Long idSubcategoria, @PathVariable Long idLinea){
        return ResponseEntity.ok(referenciaService.findReferencesByLineId(idCategoria, idSubcategoria, idLinea));
    }
}
