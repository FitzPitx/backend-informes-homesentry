package com.microservice.acumulado.microservice_acumulado.service;

import com.microservice.acumulado.microservice_acumulado.dto.*;
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
    public List<Object[]> obtainSummaryByBranch(Integer year, Integer branchId) {
        return accumulatedCategoryRepository.obtainSummaryByBranch(year, branchId);
    }

    // Method to obtain the total summary graph
    public List<SummaryTotalMonthly> getTotalSummaryGraph(Integer year, Integer sucursal) {
            List<Object[]> results = accumulatedCategoryRepository.getTotalSummaryGraph(year, sucursal);
            List<SummaryTotalMonthly> resumenList = new ArrayList<>();
            for (Object[] result : results) {
                SummaryTotalMonthly dto = new SummaryTotalMonthly();
                dto.setMonth((String) result[0]);
                dto.setTotalSaleCurrentYear(((Number) result[1]).doubleValue());
                dto.setTotalSaleLastYear(((Number) result[2]).doubleValue());
                resumenList.add(dto);
            }
            return resumenList;
        }

        // Method to obtain the total summary graph by category
        public List<SummaryTotalCategory> getTotalSummaryGraphByCategory (Integer year, Integer sucursal){
            List<Object[]> results = accumulatedCategoryRepository.getTotalSummaryGraphByCategory(year, sucursal);
            List<SummaryTotalCategory> resumenList = new ArrayList<>();
            for (Object[] result : results) {
                SummaryTotalCategory dto = new SummaryTotalCategory();
                dto.setCodigoCategoria((Integer) result[0]);
                dto.setNombreCategoria((String) result[1]);
                dto.setTotalVentasActual(((Double) result[2]));
                dto.setTotalVentasAnterior(((Double) result[3]));
                resumenList.add(dto);
            }
            return resumenList;
        }

        // Method to obtain the total profit comparison by month
        public List<SummaryTotalProfitMonthly> getTotalProfitComparisonByMonth (Integer year, Integer sucursal){
            List<Object[]> results = accumulatedCategoryRepository.getTotalProfitComparisonByMonth(year, sucursal);
            List<SummaryTotalProfitMonthly> resumenList = new ArrayList<>();
            for (Object[] result : results) {
                SummaryTotalProfitMonthly dto = new SummaryTotalProfitMonthly();
                dto.setMonth((String) result[0]);
                dto.setTotalProfitCurrentYear(((Double) result[1]));
                dto.setTotalProfitLastYear(((Double) result[2]));
                resumenList.add(dto);
            }
            return resumenList;
        }

    }
