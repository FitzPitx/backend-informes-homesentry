package com.microservice.lines.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciaIdDTO {

    private Long categoriaMri;
    private Long subcategoriaMri;
    private Long lineaMri;
    private String codMri;
}
