package com.ecom.product.dto;

import com.ecom.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {
    private Long productId;

    private String productName;

    private String category;

    private String brand;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private String imageUrl;

    private ProductStatus status;
}
