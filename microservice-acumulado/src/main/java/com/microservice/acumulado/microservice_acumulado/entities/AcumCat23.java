package com.microservice.acumulado.microservice_acumulado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "acum_cat_23")
public class AcumCat23 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acum", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @NotNull
    @Column(name = "sucursal", nullable = false)
    private Integer sucursal;

    @NotNull
    @Column(name = "fecha_mes", nullable = false)
    private Integer fechaMes;

    @NotNull
    @Column(name = "operacion_actividad", nullable = false)
    private Integer operacionActividad;

    @NotNull
    @Column(name = "bodega_origen", nullable = false)
    private Integer bodegaOrigen;

    @Size(max = 50)
    @Nationalized
    @Column(name = "tipo_producto", length = 50)
    private String tipoProducto;

    @Size(max = 50)
    @Nationalized
    @Column(name = "tipo_factura", length = 50)
    private String tipoFactura;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Double valor;

    @NotNull
    @Column(name = "valor_iva", nullable = false)
    private Double valorIva;

    @NotNull
    @Column(name = "costo", nullable = false)
    private Double costo;

}