package com.ecom.product.service.impl;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.dto.ProductUpdateRequest;
import com.ecom.product.entity.Product;
import com.ecom.product.exception.ProductNotFoundException;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .productName(request.getProductName())
                .category(request.getCategory())
                .brand(request.getBrand())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }
    @Override
    @Cacheable(value = "products")
    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy, String direction, String category, String productName) {
        Sort sort = direction.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.searchProducts(category, productName, pageable);
        return productPage.map(this::mapToResponse);
    }

    @Override
    @Cacheable(value = "productDetails", key = "#productId")
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + productId));

        return mapToResponse(product);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "products", allEntries = true),
            @CacheEvict(value = "productDetails", allEntries = true)
    })
    public ProductResponse updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + productId));

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }

        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getImageUrl() != null) {
            product.setImageUrl(request.getImageUrl());
        }

        if (request.getStatus() != null) {
            product.setStatus(request.getStatus());
        }

        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }


    private ProductResponse mapToResponse(Product product) {

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .status(product.getStatus())
                .build();
    }

}
