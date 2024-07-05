package com.example.backend.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private List<ProductDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private boolean lastPage;
}
