package com.ecom.product.service;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse addProduct(ProductRequest request);

    Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String direction,
            String category,
            String productName
    );

    ProductResponse getProductById(Long ProductId);
}
