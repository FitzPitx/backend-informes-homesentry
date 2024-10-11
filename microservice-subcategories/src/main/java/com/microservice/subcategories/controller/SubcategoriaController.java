package com.microservice.subcategories.controller;

import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import com.microservice.subcategories.service.ISubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subcategorias")
public class SubcategoriaController {

    @Autowired
    private ISubcategoriaService subcategoriaService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(subcategoriaService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(SubcategoriaId id) {
        return ResponseEntity.ok(subcategoriaService.findById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Subcategoria subcategoria) {
        subcategoriaService.save(subcategoria);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(SubcategoriaId id) {
        subcategoriaService.delete(id);
    }
}
