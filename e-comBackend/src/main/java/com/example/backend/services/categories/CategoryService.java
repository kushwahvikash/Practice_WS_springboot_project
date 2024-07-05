package com.example.backend.services.categories;

import java.util.List;

import com.example.backend.payload.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	List<CategoryDto> getAllCategory();
	CategoryDto getById(int categoryId);
	void delete(int categoryId);
 CategoryDto upateCategory(int categoryId, CategoryDto categoryDto);
}
