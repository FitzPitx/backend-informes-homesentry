package com.microservice.lines.service;

import com.microservice.lines.client.ReferenciaClient;
import com.microservice.lines.dto.ReferenciaDTO;
import com.microservice.lines.entities.Linea;
import com.microservice.lines.entities.LineaId;
import com.microservice.lines.http.response.ReferenciasByLinesResponse;
import com.microservice.lines.persistence.ILineaRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaServiceImpl implements ILineaService{

    @Autowired
    private ILineaRepository lineaRepository;

    @Autowired
    private ReferenciaClient referenciaClient;

    @Override
    public List<Linea> findAll() {
        return (List<Linea>) lineaRepository.findAll();
    }

    @Override
    public Linea findById(LineaId id) {
        return lineaRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Linea linea) {
        lineaRepository.save(linea);

    }
    @Override
    public void delete(LineaId id) {
        lineaRepository.deleteById(id);
    }

    @Override
    public List<Linea> findLinesBySubcategoryId(Integer idCategoria, Integer idSubcategoria) {
        return lineaRepository.findById_CodcatLeaAndId_CodsubLea(idCategoria, idSubcategoria);
    }

    @Override
    public ReferenciasByLinesResponse findReferencesByLineId(Integer idCategoria, Integer idSubcategoria, Integer idLinea) {

        // 1. Search lines by idLinea
        List<Linea> lineas = lineaRepository.findById_CodcatLeaAndId_CodsubLeaAndId_CodlinLea(idCategoria, idSubcategoria, idLinea);

        if(lineas.isEmpty()){
            throw  new IllegalArgumentException(("Linea no encontrada"));
        }

        Linea linea = lineaRepository.findById_CodcatLeaAndId_CodsubLeaAndId_CodlinLea(idCategoria, idSubcategoria, idLinea)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Linea no encontrada"));

        // 2. Create the list of references
        List<ReferenciaDTO> referenciaDTOList = referenciaClient.findReferencesByLineId(idCategoria, idSubcategoria, idLinea);

        return ReferenciasByLinesResponse.builder()
                .descripcionLinea(linea.getNomlineaLea())
                .referenciasDTOList(referenciaDTOList)
                .build();
    }


}
