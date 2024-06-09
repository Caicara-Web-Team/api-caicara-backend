package br.com.caicara.backend.controllers.products;

import br.com.caicara.backend.model.dto.products.ProductCreateDto;
import br.com.caicara.backend.model.dto.products.ProductResponseDto;
import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.services.products.ProductService;
import br.com.caicara.backend.model.util.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails){
        Product product = productService.createProduct(ProductMapper.toProduct(dto), userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(product));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(ProductMapper.toListDto(products));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductMapper.toDto(product));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductCreateDto dto, @PathVariable Long id){
        productService.updateProduct(id, dto);
        return ResponseEntity.noContent().build();
    }
}
