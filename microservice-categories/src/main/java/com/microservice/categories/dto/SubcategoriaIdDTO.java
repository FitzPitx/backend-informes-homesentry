package com.microservice.categories.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoriaIdDTO {
    private Integer idCategoria;
    private Integer idSubcategoria;
}
