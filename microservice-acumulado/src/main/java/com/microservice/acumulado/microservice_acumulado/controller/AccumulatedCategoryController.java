package com.microservice.acumulado.microservice_acumulado.controller;

import com.microservice.acumulado.microservice_acumulado.dto.SummaryCategoryDTO;
import com.microservice.acumulado.microservice_acumulado.dto.SummaryCategoryMonthDTO;
import com.microservice.acumulado.microservice_acumulado.dto.SummaryCategoryMonthlyDTO;
import com.microservice.acumulado.microservice_acumulado.service.AccumulatedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/acumulado")
public class AccumulatedCategoryController {

    private final AccumulatedCategoryService accumulatedCategoryService;

    @Autowired
    public AccumulatedCategoryController(AccumulatedCategoryService accumulatedCategoryService) {
        this.accumulatedCategoryService = accumulatedCategoryService;
    }

    // Endpoint para el resumen por categoría
    @GetMapping("/resumen-categorias")
    public List<SummaryCategoryDTO> getSummaryByCategory() {
        return accumulatedCategoryService.getSummaryByCategory();
    }

    // Endpoint para el resumen por mes
    @GetMapping("/resumen-categorias-mes")
    public Map<String, Object> getSummaryByCategoryByMonth(
            @RequestParam(required = false) String search) {

        // Obtiene todos los datos
        List<SummaryCategoryMonthDTO> allData = accumulatedCategoryService.getSummaryByCategoryByMonth();

        // Aplica la lógica de búsqueda (si es necesario)
        List<SummaryCategoryMonthDTO> filteredData = allData;
        if (search != null && !search.isEmpty()) {
            filteredData = allData.stream()
                    .filter(dto -> dto.getNombreCategoria().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        }

        // Prepara la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("recordsFiltered", filteredData.size());
        response.put("recordsTotal", allData.size());
        response.put("data", filteredData); // Retorna todos los datos filtrados sin paginar

        return response;
    }

    @GetMapping("/resumen-categorias-mensual")
    public List<SummaryCategoryMonthlyDTO> obtainSummaryByBranch(
            @RequestParam Integer year,
            @RequestParam(required = false) Integer sucursal) {
        // Llama al servicio para obtener los datos (ya filtrados por sucursal y año)
        List<Object[]> allData = accumulatedCategoryService.obtainSummaryByBranch(year, sucursal);

        // Mapeamos los datos a nuestro DTO personalizado para una respuesta mas legible
        List<SummaryCategoryMonthlyDTO> transformedData = allData.stream().map(data -> {
            SummaryCategoryMonthlyDTO dto = new SummaryCategoryMonthlyDTO();
            dto.setCodigoCategoria((Integer) data[0]);
            dto.setNombreCategoria((String) data[1]);
            dto.setMes((String) data[2]);
            dto.setVentaActual((Double) data[3]);
            dto.setUtilidadActual((Double) data[4]);
            dto.setMargenActual((Double) data[5]);
            dto.setVentaAnterior((Double) data[6]);
            dto.setUtilidadAnterior((Double) data[7]);
            dto.setMargenAnterior((Double) data[8]);
            dto.setDiferenciaVentas((Double) data[9]);
            dto.setDiferenciaUtilidad((Double) data[10]);
            if (data.length > 11) {
                dto.setVariacionVentas((Double) data[11]);
            }
            return dto;
        }).toList();

        return transformedData;
    }

}
