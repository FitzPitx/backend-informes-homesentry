package com.microservice.lines.http.response;


import com.microservice.lines.dto.ReferenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciasByLinesResponse {

    private String descripcionLinea;
    private List<ReferenciaDTO> referenciasDTOList;
}
