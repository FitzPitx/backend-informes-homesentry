package com.microservice.subcategories.controller;

import com.microservice.subcategories.client.LineaClient;
import com.microservice.subcategories.dto.LineaDTO;
import com.microservice.subcategories.entities.Subcategoria;
import com.microservice.subcategories.entities.SubcategoriaId;
import com.microservice.subcategories.http.response.LineasBySubcategoriesResponse;
import com.microservice.subcategories.service.ISubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subcategorias")
@CrossOrigin(origins = "http://localhost:4200")
public class SubcategoriaController {

    @Autowired
    private ISubcategoriaService subcategoriaService;



    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(subcategoriaService.findAll());
    }

    @GetMapping("/search/{idCat}/{idSub}")
    public ResponseEntity<?> findById(@PathVariable Integer idCat, @PathVariable Integer idSub) {
        SubcategoriaId subcategoriaId = new SubcategoriaId();
        subcategoriaId.setIdCategoria(idCat);
        subcategoriaId.setIdSubcategoria(idSub);

        return ResponseEntity.ok(subcategoriaService.findById(subcategoriaId));
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

    //Endpoint para ver la lista de subcategorias de una categoria
    @GetMapping("/search-by-category/{idCategoria}")
    public ResponseEntity<?> findByIdCategory(@PathVariable Integer idCategoria) {
        return ResponseEntity.ok(subcategoriaService.findSubcategorieByCategoryId(idCategoria));
    }

    //Endpoint para ver las lineas de una subcategoria
    @GetMapping("/lines-by-subcategory/{idCategoria}/{idSubcategoria}")
    public ResponseEntity<?> findLinesBySubcategories(@PathVariable Integer idCategoria, @PathVariable Integer idSubcategoria) {
        try {
            LineasBySubcategoriesResponse response = subcategoriaService.findLinesBySubcategory(idCategoria, idSubcategoria);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subcategoria no encontrada");
        }
    }

}
