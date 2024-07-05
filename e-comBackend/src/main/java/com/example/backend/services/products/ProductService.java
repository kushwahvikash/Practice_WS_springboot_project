package com.example.backend.services.products;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.backend.model.Product;
import com.example.backend.payload.ProductDto;
import com.example.backend.payload.ProductResponse;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto, int category);

	ProductResponse getAllProduct(int pageNumber,int pageSize,String sortBy,String sortDir);

	ProductDto getProductById(int productId);
	
	void deleteProductById(int product_id);


	ProductDto updateProduct(int product_id, ProductDto productDto);

	List<ProductDto> getProductByCategory(int categoryId);

}
