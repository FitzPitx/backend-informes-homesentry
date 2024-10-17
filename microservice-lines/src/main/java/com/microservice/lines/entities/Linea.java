package com.microservice.lines.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "linea")
public class Linea {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LineaId id;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "NOMLINEA_LEA", length = 100)
    private String nomlineaLea;

    @Column(name = "ESTADO_LEA")
    private Short estadoLea;

    @Column(name = "CCOSTO_LEA")
    private Short ccostoLea;

    @Column(name = "MARNAL_LEA")
    private Float marnalLea;

    @Column(name = "MARIMP_LEA")
    private Float marimpLea;

    @Column(name = "MAXMEC_LEA")
    private Float maxmecLea;

    @Column(name = "MAXPRO_LEA")
    private Float maxproLea;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "usuario_creo", length = 50)
    private String usuarioCreo;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}