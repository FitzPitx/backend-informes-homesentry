package com.microservice.acumulado.microservice_acumulado.controller;

import com.microservice.acumulado.microservice_acumulado.dto.CategoriaSucursalResumen;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenCategoriaDTO;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenCategoriaMesDTO;
import com.microservice.acumulado.microservice_acumulado.dto.ResumenSucursalDTO;
import com.microservice.acumulado.microservice_acumulado.service.AcumCat22Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/categorias22")
public class CategoriaController22 {

    private final AcumCat22Service acumCat22Service;

    @Autowired
    public CategoriaController22(AcumCat22Service acumCat22Service) {
        this.acumCat22Service = acumCat22Service;
    }

    @GetMapping("/categorias-sucursal-resumen")
    public List<CategoriaSucursalResumen> getResumen() {
        return acumCat22Service.getCategoriaSucursalResumen();
    }

    // Endpoint para el resumen por sucursal
    @GetMapping("/resumen-sucursales")
    public List<ResumenSucursalDTO> getResumenPorSucursal() {
        return acumCat22Service.getResumenPorSucursal();
    }

    // Endpoint para el resumen por categoría
    @GetMapping("/resumen-categorias")
    public List<ResumenCategoriaDTO> getResumenPorCategoria() {
        return acumCat22Service.getResumenPorCategoria();
    }

    // Endpoint para el resumen por mes
    @GetMapping("/resumen-categorias-mes")
    public Map<String, Object> getResumenPorCategoriaConMes(
            @RequestParam(required = false) String search) {

        // Obtiene todos los datos
        List<ResumenCategoriaMesDTO> allData = acumCat22Service.getResumenPorCategoriaConMes();

        // Aplica la lógica de búsqueda (si es necesario)
        List<ResumenCategoriaMesDTO> filteredData = allData;
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

}
