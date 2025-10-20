package com.FirstCrudSpring.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FirstCrudSpring.app.Entity.ProductDTO;
import com.FirstCrudSpring.app.Entity.ProductEntity;
import com.FirstCrudSpring.app.Mapper.ProductMapper;
import com.FirstCrudSpring.app.Services.Impl.ProductServiceImpl;

@RestController
@RequestMapping("api/productos")
public class ProductController {

	@Autowired
	ProductServiceImpl service;
	
	@Autowired
	ProductMapper mapper;

	@GetMapping
	public ResponseEntity<List<ProductEntity>> showAllProducts(){
		List<ProductEntity> product= service.allProducts();
		return ResponseEntity.ok(product);
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductEntity product){
		try {
			
			service.createProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(product);
			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable long id){
		try {
			service.deleteProduct(id);
			return 	ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modifyValores(@RequestBody ProductDTO product, @PathVariable long id){
		try {
			
			ProductEntity producto = mapper.toEntity(product);
			service.createProduct(producto);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
