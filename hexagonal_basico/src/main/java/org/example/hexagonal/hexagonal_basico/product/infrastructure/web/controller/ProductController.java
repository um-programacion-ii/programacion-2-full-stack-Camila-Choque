package org.example.hexagonal.hexagonal_basico.product.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.example.hexagonal.hexagonal_basico.product.domain.model.Product;
import org.example.hexagonal.hexagonal_basico.product.domain.ports.in.CreateProductUseCase;
import org.example.hexagonal.hexagonal_basico.product.domain.ports.in.GetAllProductsUseCase;
import org.example.hexagonal.hexagonal_basico.product.infrastructure.web.dto.ProductRequest;
import org.example.hexagonal.hexagonal_basico.product.infrastructure.web.dto.ProductResponse;
import org.example.hexagonal.hexagonal_basico.product.infrastructure.web.mapper.ProductDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ProductDtoMapper productDtoMapper;
    private final GetAllProductsUseCase getAllProductsUseCase;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productDtoMapper.toDomain(productRequest);
        Product createdProduct = createProductUseCase.createProduct(product);
        return new ResponseEntity<>(productDtoMapper.toResponse(createdProduct), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponses = getAllProductsUseCase.getAllProducts()
                .stream()
                .map(productDtoMapper::toResponse)
                .toList();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

}