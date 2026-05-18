package com.ecom.product.dto;

import com.ecom.product.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String productName;

    private String category;

    private String brand;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private String imageUrl;

    private ProductStatus status;
}
