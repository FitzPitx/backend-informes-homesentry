package com.microservice.categories.client;

import com.microservice.categories.dto.SubcategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-subcategories", url = "localhost:8091/api/subcategorias")
public interface CategoriaClient {

    @GetMapping("/search-by-category/{idCategory}")
    List<SubcategoriaDTO> findAllSubcategoriesByCategoryId(@PathVariable Integer idCategory);
}
