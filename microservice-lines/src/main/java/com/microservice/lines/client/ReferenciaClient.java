package com.microservice.lines.client;

import com.microservice.lines.dto.ReferenciaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-references", url = "localhost:8092/api/referencias")
public interface ReferenciaClient {

    @GetMapping("/search-by-line/{idCategoria}/{idSubcategoria}/{idLinea}")
    List<ReferenciaDTO> findReferencesByLineId(@PathVariable Integer idCategoria, @PathVariable Integer idSubcategoria, @PathVariable Integer idLinea);
}
