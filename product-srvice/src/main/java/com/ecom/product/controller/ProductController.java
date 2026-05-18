package com.ecom.product.controller;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.dto.ProductUpdateRequest;
import com.ecom.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(
            @Valid @RequestBody ProductRequest request) {

        return productService.addProduct(request);
    }

    @GetMapping
    public Page<ProductResponse> getAllProducts(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size,
                        @RequestParam(defaultValue = "productId") String sortBy,
                        @RequestParam(defaultValue = "ASC") String direction,
                        @RequestParam(defaultValue = "") String category,
                        @RequestParam(defaultValue = "") String productName
                        ){
        return productService.getAllProducts(page, size, sortBy, direction, category, productName);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(
            @PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductUpdateRequest request) {

        return productService.updateProduct(productId, request);
    }
}
