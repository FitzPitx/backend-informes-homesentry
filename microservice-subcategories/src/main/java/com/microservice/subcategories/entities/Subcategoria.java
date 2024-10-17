package com.microservice.subcategories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcategorias")
public class Subcategoria {
    @EmbeddedId
    private SubcategoriaId id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "usuario_creo", nullable = false, length = 50)
    private String usuarioCreo;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

}