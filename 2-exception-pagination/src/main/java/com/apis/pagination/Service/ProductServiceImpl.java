package com.apis.pagination.Service;

import com.apis.pagination.Entity.Product;
import com.apis.pagination.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.apis.pagination.Exception.ProductNotFound;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository repository;




    @Override
    @SneakyThrows
    public Product findById(Long id) {

        return repository.findById(id).orElseThrow(
                () -> new ProductNotFound("Product not found with id " + id)
        );
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Product product) {
         repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public Page<Product> findAvailableProducts( Pageable page) {
        return repository.findByAvailableTrue( page);
    }


}
