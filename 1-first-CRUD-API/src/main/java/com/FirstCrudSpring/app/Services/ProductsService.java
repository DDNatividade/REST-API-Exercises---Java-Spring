package com.FirstCrudSpring.app.Services;

import java.util.List;

import com.FirstCrudSpring.app.Entity.ProductEntity;



public interface ProductsService {
	
	
	public List<ProductEntity> allProducts();
	public ProductEntity findProduct(Long id);
	public void createProduct(ProductEntity product);
	public void deleteProduct(Long i);

}
