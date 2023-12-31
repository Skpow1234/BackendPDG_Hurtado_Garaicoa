package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.CategoryDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.exception.ResourceNotFoundException;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Category;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.CategoryRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        return mapToDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoryDtoList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categoryDtoList
                .stream()
                .map(
                        category -> mapToDto(category))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category", "Id", categoryId));
        return mapToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category", "Id", categoryId));
        Category newCategory = mapToEntity(categoryDTO);
        category.setDescription(newCategory.getDescription());
        category.setName(newCategory.getName());
        categoryRepository.save(category);
        return mapToDto(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category", "Id", categoryId));
        categoryRepository.deleteById(categoryId);
        return "Category successfully  deleted.";
    }

    private CategoryDTO mapToDto(Category category){
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    private Category mapToEntity(CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }
}
