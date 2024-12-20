package com.microservice.categories.controller;


import com.microservice.categories.entities.Categoria;
import com.microservice.categories.http.response.SubcategoriesByCategoriesResponse;
import com.microservice.categories.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.getCategoryById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody Categoria categoria) {
        categoriaService.saveCategory(categoria);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoriaService.deleteCategory(id);
    }

    //Dejar esta metodo REST para mas adelante por ahora solo los reportes
    @PutMapping("/update/{id}")
    public void updateCategory(Integer id, @RequestBody Categoria categoria) {
        categoriaService.updateCategory(id, categoria);
    }

    @GetMapping("/search-subcategories/{idCategoria}")
    public ResponseEntity<?> findAllSubcategoriesByCategoryId(@PathVariable Integer idCategoria) {
        try {
            SubcategoriesByCategoriesResponse response = categoriaService.findSubcategoriesByIdCategory(idCategoria);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }
}
