package com.apis.pagination.Service;

import com.apis.pagination.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Product findById (Long id);
    public Page<Product> findAll(Pageable page);
    public void save(Product product);
    public void delete(Product product);
    public Page<Product> findAvailableProducts (Pageable page);



}
