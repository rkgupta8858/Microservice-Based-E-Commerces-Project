package com.ecom.product.repository;

import com.ecom.product.entity.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT p FROM Product p
            WHERE LOWER(p.category)
            LIKE LOWER(CONCAT('%', :category, '%'))
            AND LOWER(p.productName)
            LIKE LOWER(CONCAT('%', :productName, '%'))
            """)
    Page<Product> searchProducts(
           @Param("category") String category,
           @Param("productName") String productName,
           Pageable pageable
    );

}
