package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.payload.ApiResponse;
import com.example.backend.payload.CategoryDto;
import com.example.backend.services.categories.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//create api
	@PostMapping("/create")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
	}
	//get Api by id
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable int categoryId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.getById(categoryId));
	}
	//get All category
	@GetMapping
	public ResponseEntity<List<?>> getAllCategory() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.getAllCategory());
	}
	
	//update api by id
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<?> upateCategory(@PathVariable int categoryId, @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.upateCategory(categoryId,categoryDto));
	}

	//Delete api by id
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteById(@PathVariable int categoryId) {
		categoryService.delete(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully!",true),HttpStatus.OK);
		
	}
}
