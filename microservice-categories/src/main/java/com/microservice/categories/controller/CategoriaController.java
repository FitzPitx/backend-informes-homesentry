package com.microservice.categories.controller;


import com.microservice.categories.entities.Categoria;
import com.microservice.categories.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoriaService.getAllCategory());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> getCategoryById(Integer id) {
        return ResponseEntity.ok(categoriaService.getCategoryById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody Categoria categoria) {
        categoriaService.saveCategory(categoria);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(Integer id) {
        categoriaService.deleteCategory(id);
    }

    //Dejar esta metodo REST para mas adelante por ahora solo los reportes
    @PutMapping("/update/{id}")
    public void updateCategory(Integer id, @RequestBody Categoria categoria) {
        categoriaService.updateCategory(id, categoria);
    }
}
