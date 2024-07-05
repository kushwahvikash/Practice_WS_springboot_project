package com.example.backend.services.categories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.model.Category;
import com.example.backend.payload.CategoryDto;
import com.example.backend.reposotory.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	public CategoryDto createCategory(CategoryDto categoryDto) {

		// categoryDto to Category
		Category mapCat = this.modelMapper.map(categoryDto, Category.class);
		categoryRepository.save(mapCat);

		// category to CategoryDto
		return modelMapper.map(mapCat, CategoryDto.class);
	}

	public CategoryDto upateCategory(int categoryId, CategoryDto newDto) {
     Category oldCat= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" not found in db!..."));
		oldCat.setCategoryId(newDto.getCategoryId());
		oldCat.setTitle(newDto.getTitle());
      return modelMapper.map(oldCat, CategoryDto.class);
	}

	public void delete(int categoryId) {
		Category cat= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" not found in db!..."));
		categoryRepository.delete(cat);
	}

	public CategoryDto getById(int categoryId) {
		Category getById= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" not found in db!..."));
		
		return modelMapper.map(getById, CategoryDto.class);

	}

	public List<CategoryDto> getAllCategory() {
  List<CategoryDto> allDto=(categoryRepository.findAll()).stream().map(cate->modelMapper.map(cate, CategoryDto.class)).collect(Collectors.toList());
		return allDto;
	}
}
