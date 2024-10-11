package com.microservice.categories.service;

import com.microservice.categories.client.CategoriaClient;
import com.microservice.categories.dto.SubcategoriaDTO;
import com.microservice.categories.entities.Categoria;
import com.microservice.categories.http.response.SubcategoriesByCategoriesResponse;
import com.microservice.categories.persistence.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaClient categoriaClient;

    @Override
    public List<Categoria> getAllCategory() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoryById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // Dejar estos metodos para mas adelante por ahora solo los reportes
    @Override
    public Categoria saveCategory(Categoria supplier) {
        return categoriaRepository.save(supplier);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Integer id, Categoria categoria) {
        Categoria categoriaToUpdate = categoriaRepository.findById(id).orElse(null);
        if (categoriaToUpdate != null) {
            categoriaToUpdate.setNomcatCeg(categoria.getNomcatCeg());
            categoriaToUpdate.setEstadoCeg(categoria.getEstadoCeg());
            categoriaToUpdate.setJefcomCeg(categoria.getJefcomCeg());
            categoriaToUpdate.setUsuarioCreo(categoria.getUsuarioCreo());
            categoriaToUpdate.setFechaCreacion(categoria.getFechaCreacion());
            categoriaToUpdate.setCodsgeCeg(categoria.getCodsgeCeg());
            categoriaToUpdate.setCodrolCeg(categoria.getCodrolCeg());
            categoriaToUpdate.setExcluidoCeg(categoria.getExcluidoCeg());
            categoriaRepository.save(categoriaToUpdate);
        }
    }

    @Override
    public SubcategoriesByCategoriesResponse getSubcategoriesByCategoryId(Integer idCategory) {
        // Consultar las subcategorias por categoria
        Categoria categoria = categoriaRepository.findById(idCategory).orElse(new Categoria());
        // Obtener las subcategorias por categoria
        List<SubcategoriaDTO> subcategoriaDTOList = categoriaClient.findAllSubcategoriesByCategoryId(idCategory);
        return SubcategoriesByCategoriesResponse.builder()
                .nomsubcatSce(categoria.getNomcatCeg())
                .estadoSce(categoria.getEstadoCeg())
                .usuarioCreo(categoria.getUsuarioCreo())
                .fechaCreacion(categoria.getFechaCreacion())
                .subcategoriesDTOList(subcategoriaDTOList)
                .build();
    }


}
