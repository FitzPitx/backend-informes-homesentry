package com.microservice.subcategories.http.response;

import com.microservice.subcategories.dto.LineaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineasBySubcategoriesResponse {

    private String descripcion;
    private List<LineaDTO> lineasDTOList;
}
