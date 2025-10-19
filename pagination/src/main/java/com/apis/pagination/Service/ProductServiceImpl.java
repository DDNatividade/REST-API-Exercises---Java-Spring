package com.apis.pagination.Service;

import com.apis.pagination.Entity.Product;
import com.apis.pagination.Repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apis.pagination.Exception.ProductNotFound;
import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @SneakyThrows
    public Product findById(Long id) {

        return repository.findById(id).orElseThrow(()
                -> new ProductNotFound("Product not found"));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Product product) {
         repository.save(product);
    }


}
