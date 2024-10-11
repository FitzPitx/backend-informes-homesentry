package com.microservice.categories.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoriaDTO {

    private String nomsubcatSce;
    private Short estadoSce;
    private String usuarioCreo;
    private String fechaCreacion;
}
