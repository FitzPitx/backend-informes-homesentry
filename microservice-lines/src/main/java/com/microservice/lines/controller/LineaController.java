package com.microservice.lines.controller;

import com.microservice.lines.entities.Linea;
import com.microservice.lines.entities.LineaId;
import com.microservice.lines.service.ILineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/lineas")
public class LineaController {

    @Autowired
    private ILineaService lineaService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(lineaService.findAll());
    }

    //Cambiar el id para que ser reciban con las tres llaves de Categoria, subcategoria, y Linea
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable LineaId id) {
        return ResponseEntity.ok(lineaService.findById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@RequestBody Linea linea) {
        lineaService.save(linea);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable LineaId id) {
        lineaService.delete(id);
    }

    //Endpoint para ver la lista de lineas de una subcategoria
    @GetMapping("/search-by-subcategory/{idCategoria}/{idSubcategoria}")
    public ResponseEntity<?> findByIdSubcategory(@PathVariable Integer idCategoria, @PathVariable Integer idSubcategoria){
        return ResponseEntity.ok(lineaService.findLinesBySubcategoryId(idCategoria, idSubcategoria));
    }
}
