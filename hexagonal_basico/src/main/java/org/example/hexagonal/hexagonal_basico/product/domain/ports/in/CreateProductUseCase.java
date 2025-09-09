package org.example.hexagonal.hexagonal_basico.product.domain.ports.in;

import org.example.hexagonal.hexagonal_basico.product.domain.model.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);

}