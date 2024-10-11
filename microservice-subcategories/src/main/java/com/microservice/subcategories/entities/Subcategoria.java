package com.microservice.subcategories.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcategorias")
public class Subcategoria {
    @EmbeddedId
    private SubcategoriaId id;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "NOMSUBCAT_SCE", length = 100)
    private String nomsubcatSce;

    @Column(name = "ESTADO_SCE")
    private Short estadoSce;

    @jakarta.validation.constraints.Size(max = 12)
    @Column(name = "usuario_creo", length = 12)
    private String usuarioCreo;

    @jakarta.validation.constraints.Size(max = 21)
    @Column(name = "fecha_creacion", length = 21)
    private String fechaCreacion;

}