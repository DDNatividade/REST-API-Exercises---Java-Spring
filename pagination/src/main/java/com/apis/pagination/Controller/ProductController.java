package com.apis.pagination.Controller;

import com.apis.pagination.DTO.ProductDTO;
import com.apis.pagination.Entity.Product;
import com.apis.pagination.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductMapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product= productService.findAll();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyValores(@RequestBody @Valid ProductDTO productDTO, @PathVariable long id) {
        Product existingProduct = productService.findById(id);

        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setName(productDTO.getName());
        existingProduct.setAvailable(productDTO.isAvailable());

        productService.save(existingProduct);

        return ResponseEntity.ok().build();
    }

}
