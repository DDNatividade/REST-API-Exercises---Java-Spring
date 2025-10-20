package com.apis.pagination.Controller;

import com.apis.pagination.DTO.PagedResponse;
import com.apis.pagination.DTO.ProductDTO;
import com.apis.pagination.Entity.Product;
import com.apis.pagination.Repository.ProductRepository;
import com.apis.pagination.Service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Product>> getAllProducts(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        Page<Product> product= productService.findAll(pageable);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        productService.delete(productService.findById(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/true")

        public ResponseEntity<PagedResponse<ProductDTO>> getAvailableProducts(
                @PageableDefault(size = 10, sort = "id") Pageable pageable) {

            Page<Product> page = productService.findAvailableProducts(pageable);

            // Mapea cada entidad a DTO
            List<ProductDTO> content = page.getContent().stream()
                    .map(product -> mapper.toProductDTO(product))
                    .toList();

            // Devuelve un objeto limpio con info de paginación
            PagedResponse<ProductDTO> response = new PagedResponse<>(
                    content,
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.isLast()
            );

            return ResponseEntity.ok(response);
        }


}
