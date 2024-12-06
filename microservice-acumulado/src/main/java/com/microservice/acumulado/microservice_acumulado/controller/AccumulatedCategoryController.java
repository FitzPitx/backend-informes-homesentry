package com.microservice.acumulado.microservice_acumulado.controller;

import com.microservice.acumulado.microservice_acumulado.dto.*;
import com.microservice.acumulado.microservice_acumulado.service.AccumulatedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public List<Map<String, Object>> obtainSummaryByBranch(
            @RequestParam Integer year,
            @RequestParam(required = false) Integer sucursal) {
        // Llama al servicio para obtener los datos (ya filtrados por sucursal y año)
        List<Object[]> allData = accumulatedCategoryService.obtainSummaryByBranch(year, sucursal);

        // Lista para almacenar la respuesta final
        List<Map<String, Object>> response = new ArrayList<>();

        // Mapa temporal para agrupar categorías
        Map<Integer, LinkedHashMap<String, Object>> categoryMap = new LinkedHashMap<>();

        for (Object[] data : allData) {
            Integer codigoCategoria = (Integer) data[0];
            String nombreCategoria = (String) data[1];
            String mes = data[2].toString().toLowerCase(); // Usar minúsculas para las claves de los meses

            // Obtener o crear un mapa para la categoría
            LinkedHashMap<String, Object> categoryData = categoryMap.getOrDefault(codigoCategoria, new LinkedHashMap<>());
            categoryData.putIfAbsent("codigo_categoria", codigoCategoria);
            categoryData.putIfAbsent("nombre_categoria", nombreCategoria);

            // Obtener o crear un mapa para el mes
            LinkedHashMap<String, Object> monthData = (LinkedHashMap<String, Object>) categoryData.getOrDefault(mes, new LinkedHashMap<>());

            // Agregar datos al mapa del mes
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-ventaActual", (Double) data[3]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-utilidadActual", (Double) data[4]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-margenActual", (Double) data[5]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-ventaAnterior", (Double) data[6]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-utilidadAnterior", (Double) data[7]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-margenAnterior", (Double) data[8]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-diferenciaVentas", (Double) data[9]);
            monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-diferenciaUtilidad", (Double) data[10]);
            if (data.length > 11) {
                monthData.put(mes.substring(0, 1).toUpperCase() + mes.substring(1) + "-variacionVentas", (Double) data[11]);
            }

            // Guardar el mapa del mes en la categoría
            categoryData.put(mes, monthData);

            // Actualizar el mapa de categorías
            categoryMap.put(codigoCategoria, categoryData);
        }

        // Convertir el mapa agrupado en una lista
        response.addAll(categoryMap.values());
        return response;
    }

    @GetMapping("/resumen-total-mensual-grafica")
    public List<SummaryTotalMonthly> getTotalSummayGraph(
            @RequestParam Integer year,
            @RequestParam(required = false) Integer sucursal)
    {
        return accumulatedCategoryService.getTotalSummaryGraph(year, sucursal);
    }

    @GetMapping("/resumen-total-mensual-grafica-categoria")
    public List<SummaryTotalCategory> getTotalSummaryGraphByCategory(
            @RequestParam Integer year,
            @RequestParam(required = false) Integer sucursal)
    {
        return accumulatedCategoryService.getTotalSummaryGraphByCategory(year, sucursal);
    }

    @GetMapping("/comparacion-utilidad-mensual")
    public List<SummaryTotalProfitMonthly> getTotalProfitComparisonByMonth(
            @RequestParam Integer year,
            @RequestParam(required = false) Integer sucursal)
    {
        return accumulatedCategoryService.getTotalProfitComparisonByMonth(year, sucursal);
    }


}
