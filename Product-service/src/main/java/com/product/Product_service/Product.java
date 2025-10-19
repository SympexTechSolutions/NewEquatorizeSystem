package com.product.Product_service;
import com.product.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name  = "products")
@Builder
public class Product {
  @Id 
  @GeneratedValue(strategy  = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  private String description;

  @NotNull @Positive
  private Double price;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

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

public Product(@NotBlank String name, String description, @NotNull @Positive Double price) {
	super();
	this.name = name;
	this.description = description;
	this.price = price;
}
  
  
  
}