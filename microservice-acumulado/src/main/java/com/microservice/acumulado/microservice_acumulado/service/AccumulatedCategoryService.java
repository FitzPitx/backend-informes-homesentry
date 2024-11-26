package com.microservice.acumulado.microservice_acumulado.service;

import com.microservice.acumulado.microservice_acumulado.dto.SummaryCategoryDTO;
import com.microservice.acumulado.microservice_acumulado.dto.SummaryCategoryMonthDTO;
import com.microservice.acumulado.microservice_acumulado.repository.AccumulatedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccumulatedCategoryService {

    private final AccumulatedCategoryRepository accumulatedCategoryRepository;

    @Autowired
    public AccumulatedCategoryService(AccumulatedCategoryRepository accumulatedCategoryRepository) {
        this.accumulatedCategoryRepository = accumulatedCategoryRepository;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to obtain the summary by category
    public List<SummaryCategoryDTO> getSummaryByCategory() {
        List<Object[]> results = accumulatedCategoryRepository.findSummaryByCategory();
        List<SummaryCategoryDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            SummaryCategoryDTO dto = new SummaryCategoryDTO();
            dto.setCodigoCategoria((Integer) result[0]);
            dto.setNombreCategoria((String) result[1]);
            dto.setTotalCantidad(((Number) result[2]).longValue());
            dto.setTotalValor(((Number) result[3]).doubleValue());
            dto.setTotalValorIva(((Number) result[4]).doubleValue());
            dto.setTotalCosto(((Number) result[5]).doubleValue());
            resumenList.add(dto);
        }
        return resumenList;
    }

    // Method to obtain the summary by month of categories
    public List<SummaryCategoryMonthDTO> getSummaryByCategoryByMonth() {
        List<Object[]> results = accumulatedCategoryRepository.findSummaryByCategoryByMonth();
        List<SummaryCategoryMonthDTO> resumenList = new ArrayList<>();

        for (Object[] result : results) {
            SummaryCategoryMonthDTO dto = new SummaryCategoryMonthDTO();
            dto.setCodigoCategoria((Integer) result[0]);
            dto.setNombreCategoria((String) result[1]);
            dto.setNombreMes((String) result[2]);
            dto.setTotalCantidad(((Number) result[3]).longValue());
            dto.setTotalValor(((Number) result[4]).doubleValue());
            dto.setTotalValorIva(((Number) result[5]).doubleValue());
            dto.setTotalCosto(((Number) result[6]).doubleValue());
            resumenList.add(dto);
        }
        return resumenList;
    }

    // Method to obtain the summary by branch
    public List<Object[]> obtainSummaryByBranch(Integer branchId, Integer year) {
        return accumulatedCategoryRepository.obtainSummaryByBranch(branchId, year);
    }

}