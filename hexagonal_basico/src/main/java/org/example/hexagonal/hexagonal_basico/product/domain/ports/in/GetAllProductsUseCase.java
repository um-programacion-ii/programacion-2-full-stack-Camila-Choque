package org.example.hexagonal.hexagonal_basico.product.domain.ports.in;

import org.example.hexagonal.hexagonal_basico.product.domain.model.Product;
import java.util.List;

public interface GetAllProductsUseCase {

    List<Product> getAllProducts();

}
