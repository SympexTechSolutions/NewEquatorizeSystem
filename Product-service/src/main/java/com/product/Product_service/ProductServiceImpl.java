package com.product.Product_service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository repository;
	
	public ProductServiceImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}
	
    @Override
	@Transactional
	  public ProductResponse create(ProductCreateRequest req) {
		  Product p = new Product(req.getName(), req.getDescription(), req.getPrice());
		  Product saved = repository.save(p);
		  return new ProductResponse(saved.getId(), saved.getName(), saved.getDescription(), saved.getPrice());
		}
	  @Override
	  public List<ProductResponse> findAll() {
	    return repository.findAll().stream().map(this::toResponse).toList();
	  }

	  @Override
	  public ProductResponse findById(Long id) {
	    Product p = repository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Product not found: " + id));
	    return toResponse(p);
	  }

	  private ProductResponse toResponse(Product p) {
		    return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice());
		  }
	

}
