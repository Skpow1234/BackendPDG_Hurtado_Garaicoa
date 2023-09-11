package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryById(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);

    String deleteCategory(Long categoryId);
}
