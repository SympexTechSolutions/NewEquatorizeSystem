package com.product.Product_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductDto {
	@NotBlank
	  private String name;
	  private String description;
	  @NotNull @Positive
	  private Double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public ProductDto(@NotBlank String name, String description, @NotNull @Positive Double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	
	  
}
