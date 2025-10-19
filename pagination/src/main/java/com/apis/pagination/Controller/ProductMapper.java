package com.apis.pagination.Controller;

import com.apis.pagination.DTO.ProductDTO;
import com.apis.pagination.Entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    ModelMapper modelMapper;

    public Product toProduct(ProductDTO productDTO) {
        return  modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO toProductDTO(Product product) {
        return  modelMapper.map(product, ProductDTO.class);
    }



}
