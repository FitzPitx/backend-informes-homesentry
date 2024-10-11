package com.microservice.branches.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursales")
public class Sucursal {
    @Id
    @jakarta.validation.constraints.NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODSUC_SUC", nullable = false)
    private Short id;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "DESSUC_SUC", nullable = false, length = 100)
    private String dessucSuc;

    @jakarta.validation.constraints.NotNull
    @Column(name = "CODACT_SUC", nullable = false)
    private Short codactSuc;

    @Column(name = "TIPSUC_SUC")
    private Short tipsucSuc;

    @Column(name = "CODARQ_SUC")
    private Short codarqSuc;

    @jakarta.validation.constraints.Size(max = 10)
    @Column(name = "NMIPSO_SUC", length = 10)
    private String nmipsoSuc;

    @Column(name = "NUMPIS_SUC")
    private Short numpisSuc;

    @Column(name = "FECAPE_SUC")
    private Instant fecapeSuc;

    @jakarta.validation.constraints.Size(max = 1)
    @Column(name = "INDCRO_SUC", length = 1)
    private String indcroSuc;

    @Column(name = "ESTADO_SUC")
    private Short estadoSuc;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "CENOPE_SUC", length = 50)
    private String cenopeSuc;

    @jakarta.validation.constraints.Size(max = 4)
    @Column(name = "PREFIJ_SUC", length = 4)
    private String prefijSuc;

    @Column(name = "CODCIU_SUC")
    private Integer codciuSuc;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "USUCRE_SUC", length = 50)
    private String usucreSuc;

    @Column(name = "FECCRE_SUC")
    private Instant feccreSuc;

    @Column(name = "TIPCOB_SUC")
    private Short tipcobSuc;

    @Column(name = "EOCWMS_SUC")
    private Short eocwmsSuc;

    @Column(name = "EPEWMS_SUC")
    private Short epewmsSuc;

    @Column(name = "ORDIMP_SUC")
    private Short ordimpSuc;

    @Column(name = "ORDNAC_SUC")
    private Short ordnacSuc;

    @Column(name = "DISIMP_SUC")
    private Short disimpSuc;

    @Column(name = "DISNAC_SUC")
    private Short disnacSuc;

    @Column(name = "TIPNEG_SUC")
    private Short tipnegSuc;

    @Column(name = "TIERAP_SUC")
    private Short tierapSuc;

    @Column(name = "ETRWMS_SUC")
    private Short etrwmsSuc;

    @Column(name = "METROS_SUC")
    private Short metrosSuc;

}