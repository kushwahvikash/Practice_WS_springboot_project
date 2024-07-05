package com.example.backend.payload;

import lombok.Data;

@Data
public class ProductDto {

	private int product_id;
	private String product_name;
	private double product_prize;
	private boolean stock;
	private int product_quantity;
	private boolean live;
	private String product_img;
	private String product_description;
	private CategoryDto categoryDto;
}