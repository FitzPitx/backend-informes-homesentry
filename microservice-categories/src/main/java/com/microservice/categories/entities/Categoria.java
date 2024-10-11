package com.microservice.categories.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CEG", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 28)
    @Column(name = "NOMCAT_CEG", length = 28)
    private String nomcatCeg;

    @Column(name = "ESTADO_CEG")
    private Short estadoCeg;

    @jakarta.validation.constraints.Size(max = 6)
    @Column(name = "JEFCOM_CEG", length = 6)
    private String jefcomCeg;

    @jakarta.validation.constraints.Size(max = 10)
    @Column(name = "usuario_creo", length = 10)
    private String usuarioCreo;

    @jakarta.validation.constraints.Size(max = 12)
    @Column(name = "fecha_creacion", length = 12)
    private String fechaCreacion;

    @jakarta.validation.constraints.Size(max = 4)
    @Column(name = "CODSGE_CEG", length = 4)
    private String codsgeCeg;

    @jakarta.validation.constraints.Size(max = 4)
    @Column(name = "CODROL_CEG", length = 4)
    private String codrolCeg;

    @Column(name = "EXCLUIDO_CEG")
    private Short excluidoCeg;

}