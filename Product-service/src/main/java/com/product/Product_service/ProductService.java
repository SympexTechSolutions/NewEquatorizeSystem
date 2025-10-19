package com.product.Product_service;

import java.util.List;

public interface ProductService {
	ProductResponse create(ProductCreateRequest request);
	List<ProductResponse> findAll();
	ProductResponse findById(Long id);
}
