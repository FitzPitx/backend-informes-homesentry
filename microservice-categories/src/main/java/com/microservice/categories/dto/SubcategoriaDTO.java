package com.microservice.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoriaDTO {

    private SubcategoriaIdDTO id;
    private String descripcion;
    private Short estado;
    private String usuarioCreo;
    private String fechaCreacion;
}
