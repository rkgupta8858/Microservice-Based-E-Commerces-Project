package com.ecom.product.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
