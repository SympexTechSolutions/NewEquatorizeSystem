package com.product.Product_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductCreateRequest {
	@NotBlank(message = "Name is required")
	  @Size(min = 2, max = 125, message = "Name must be 2-125 characters")
	  private String name;

	  @Size(max = 500, message = "Description must be at most 500 characters")
	  private String description;

	  @NotNull(message = "Price is required")
	  @Positive(message = "Price must be positive")
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
	
	 public ProductCreateRequest() {
	    }

	public ProductCreateRequest(
			@NotBlank(message = "Name is required") @Size(min = 2, max = 125, message = "Name must be 2-125 characters") String name,
			@Size(max = 500, message = "Description must be at most 500 characters") String description,
			@NotNull(message = "Price is required") @Positive(message = "Price must be positive") Double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	  
	
}
