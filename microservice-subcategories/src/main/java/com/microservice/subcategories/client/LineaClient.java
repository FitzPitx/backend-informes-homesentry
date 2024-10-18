package com.microservice.subcategories.client;

import com.microservice.subcategories.dto.LineaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-lines", url = "localhost:8093/api/lineas")
public interface LineaClient {

    @GetMapping("/search-by-subcategory/{idCategoria}/{idSubcategoria}")
    List<LineaDTO> findLinesBySubcategoryId(@PathVariable Integer idCategoria, @PathVariable Integer idSubcategoria);
}
