package com.apis.pagination.Service;

import com.apis.pagination.Entity.Product;

import java.util.List;

public interface ProductService {
    public Product findById(Long id);
    public List<Product> findAll();
    public void save(Product product);

}
