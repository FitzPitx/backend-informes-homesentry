package com.microservice.categories.http.response;

import com.microservice.categories.dto.SubcategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubcategoriesByCategoriesResponse {

    private String descripcion;
    private List<SubcategoriaDTO> subcategoriesDTOList;
}
