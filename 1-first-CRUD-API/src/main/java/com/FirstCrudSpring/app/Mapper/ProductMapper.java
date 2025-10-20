package com.FirstCrudSpring.app.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.FirstCrudSpring.app.Entity.ProductDTO;
import org.springframework.stereotype.Component;
import com.FirstCrudSpring.app.Entity.ProductEntity;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductEntity toEntity(ProductDTO productoDTO){
        return modelMapper.map(productoDTO,ProductEntity.class);
    }

    public void toEntity(ProductDTO productoDTO,ProductEntity productoExistente){
        modelMapper.map(productoDTO,productoExistente);
    }

    public ProductDTO toDTO(ProductEntity producto){
        return modelMapper.map(producto,ProductDTO.class);
    }
}