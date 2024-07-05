package com.example.backend.services.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.model.Category;
import com.example.backend.model.Product;
import com.example.backend.payload.CategoryDto;
import com.example.backend.payload.ProductDto;
import com.example.backend.payload.ProductResponse;
import com.example.backend.reposotory.CategoryRepository;
import com.example.backend.reposotory.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public ProductDto createProduct(ProductDto productDto, int categoryId) {
		// fetch category is available or not
		Category cat = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("this id isnot exist in db!"));
		// productDto to Product
		Product productEntity = toEntity(productDto);
		productEntity.setCategory(cat);
		Product save = productRepository.save(productEntity);
		// product to ProductDto
		ProductDto dto = toDto(save);
		return dto;
	}

	/*
	 * public List<?> getAllProduct() { return productRepository.findAll(); }
	 */

	public ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = null;
		if (sortDir.trim().toLowerCase().equals("asc")) {
			//
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> page = productRepository.findAll(pageable);
		List<Product> pageProduct = page.getContent();
		List<Product> product = pageProduct.stream().filter(p -> p.isLive()).collect(Collectors.toList());

		List<ProductDto> productDto = product.stream().map(p -> this.toDto(p)).collect(Collectors.toList());

		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDto);
		productResponse.setPageNumber(page.getNumber());
		productResponse.setPageSize(page.getSize());
		productResponse.setTotalPages(page.getTotalPages());
		productResponse.setLastPage(page.isLast());

		// List<Product> findAll=productRepository.findAll();
		// product to productDto
		// List<ProductDto>
		// findAllDto=findAll.stream().map(p->this.toDto(p)).collect(Collectors.toList());
		return productResponse;
	}

	public ProductDto getProductById(int product_id) {
		Product findProductById = productRepository.findById(product_id)
				.orElseThrow(() -> new ResourceNotFoundException(product_id + " product not available in db!..."));
		ProductDto dto = this.toDto(findProductById);
		return dto;
	}

	public void deleteProductById(int product_id) {
		productRepository.findById(product_id)
				.orElseThrow(() -> new ResourceNotFoundException(product_id + " product not exist in db!..."));
		productRepository.deleteById(product_id);

	}

	public ProductDto updateProduct(int product_id, ProductDto newProduct) {

		Product oldProduct = productRepository.findById(product_id)
				.orElseThrow(() -> new ResourceNotFoundException(product_id + " product not available in db!..."));
		oldProduct.setProduct_name(newProduct.getProduct_name());
		oldProduct.setProduct_prize(newProduct.getProduct_prize());
		oldProduct.setLive(newProduct.isLive());
		oldProduct.setStock(newProduct.isStock());
		oldProduct.setProduct_description(newProduct.getProduct_description());
		oldProduct.setProduct_quantity(newProduct.getProduct_quantity());
		oldProduct.setProduct_img(newProduct.getProduct_img());
		// convert in toDto method
		ProductDto dto = toDto(productRepository.save(oldProduct));
		return dto;
	}

	// find product by category
	public List<ProductDto> getProductByCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(categoryId + " is not exist in db!"));
		List<Product> findByCategory = productRepository.findByCategory(category);
		List<ProductDto> productDtos = findByCategory.stream().map(product -> toDto(product))
				.collect(Collectors.toList());

		return productDtos;
	}

	// productDto to Product
	public Product toEntity(ProductDto pDto) {

		Product p = new Product();
		p.setProduct_id(pDto.getProduct_id());
		p.setProduct_name(pDto.getProduct_name());
		p.setProduct_prize(pDto.getProduct_prize());
		p.setLive(pDto.isLive());
		p.setStock(pDto.isStock());
		p.setProduct_description(pDto.getProduct_description());
		p.setProduct_quantity(pDto.getProduct_quantity());
		p.setProduct_img(pDto.getProduct_img());
		return p;
	}

	// product to ProductDto
	public ProductDto toDto(Product p) {

		ProductDto pDto = new ProductDto();
		pDto.setProduct_id(p.getProduct_id());
		pDto.setProduct_name(p.getProduct_name());
		pDto.setProduct_prize(p.getProduct_prize());
		pDto.setLive(p.isLive());
		pDto.setStock(p.isStock());
		pDto.setProduct_description(p.getProduct_description());
		pDto.setProduct_quantity(p.getProduct_quantity());
		pDto.setProduct_img(p.getProduct_img());
		// change Category to CategoryDto
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(p.getCategory().getCategoryId());
		categoryDto.setTitle(p.getCategory().getTitle());
		// then set CategoryDto in productDto
		pDto.setCategoryDto(categoryDto);
		return pDto;
	}

}
