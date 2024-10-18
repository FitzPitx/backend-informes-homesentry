package com.microservice.subcategories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineaDTO {

    private LineaIdDTO id;
    private String nomlineaLea;
    private Short estadoLea;
    private Short ccostoLea;
    private Float marnalLea;
    private Float marimpLea;
    private Float maxmecLea;
    private Float maxproLea;
    private String usuarioCreo;
    private String fechaCreacion;
}
