package com.FirstCrudSpring.app.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.Entity.ProductEntity;
import com.FirstCrudSpring.app.ProductRepositoy.ProductRepository;
import com.FirstCrudSpring.app.Services.ProductsService;

import lombok.SneakyThrows;

@Service
public class ProductServiceImpl  implements ProductsService{
	
	
	@Autowired
	ProductRepository repo;
	

	@Override
	public List<ProductEntity> allProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	@SneakyThrows
	public ProductEntity findProduct(Long id) {
		ProductEntity product=repo.findById(id).orElseThrow(() -> new Exception("No está el producto"));
		return product;
	}

	@Override
	public void createProduct(ProductEntity product) {
		repo.save(product);
		
	}

	@Override
	public void deleteProduct(Long id) {
		repo.deleteById(id);
	}





}
