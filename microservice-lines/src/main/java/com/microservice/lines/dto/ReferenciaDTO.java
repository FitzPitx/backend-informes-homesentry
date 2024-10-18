package com.microservice.lines.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciaDTO {
    private ReferenciaIdDTO id;
    private String descodMri;
    private Double costoMri;
    private Integer pvtaMri;
    private String ivaMri;
    private Double cosdolMri;
    private String tipoprodMri;
    private String ultcosMri;
    private String proveedMri;
}
