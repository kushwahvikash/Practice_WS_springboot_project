package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Product;
import com.example.backend.payload.AppConstants;
import com.example.backend.payload.ProductDto;
import com.example.backend.payload.ProductResponse;
import com.example.backend.services.products.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/create/{categoryId}")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto, @PathVariable int categoryId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto, categoryId));
	}

	@GetMapping
	public ProductResponse getAllProduct(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING, required = false) String sortDir) {
		ProductResponse productResponse = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);

		return productResponse;
	}

	@GetMapping("/{product_id}")
	public ResponseEntity<?> getProductById(@PathVariable int product_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(product_id));
		// return
		// ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getProductById(product_id));
	}

	@DeleteMapping("/{product_id}")
	public ResponseEntity<?> deleteProductById(@PathVariable int product_id) {
		productService.deleteProductById(product_id);
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);

	}

	@PutMapping("/update/{product_id}")
	public ResponseEntity<?> updateProduct(@PathVariable int product_id, @RequestBody ProductDto productDto) {

		ProductDto updatedProduct = productService.updateProduct(product_id, productDto);
		return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.ACCEPTED);

	}

	// find product by category wise
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable int categoryId) {
		List<ProductDto> findProductByCategory = productService.getProductByCategory(categoryId);
		return new ResponseEntity<List<ProductDto>>(findProductByCategory, HttpStatus.ACCEPTED);
	}

}
