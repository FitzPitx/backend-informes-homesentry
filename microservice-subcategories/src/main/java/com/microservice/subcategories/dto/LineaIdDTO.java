package com.microservice.subcategories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineaIdDTO {
    private Integer codlinLea;
    private Integer codsubLea;
    private Integer codcatLea;
}
