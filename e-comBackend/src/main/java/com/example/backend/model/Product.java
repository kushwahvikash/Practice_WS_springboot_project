package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private int product_id;
	private String product_name;
	private double product_prize;
	private boolean stock;
	private int product_quantity;
	private boolean live;
	private String product_img;
	private String product_description;
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
}
