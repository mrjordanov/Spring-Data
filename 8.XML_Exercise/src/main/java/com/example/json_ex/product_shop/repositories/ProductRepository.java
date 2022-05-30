package com.example.json_ex.product_shop.repositories;

import com.example.json_ex.product_shop.entities.products.Product;
import com.example.json_ex.product_shop.entities.products.ProductOneDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.json_ex.product_shop.entities.products.ProductOneDTO(" +
            "p.name, p.price,p.seller.firstName,p.seller.lastName) from Product p where p.price>:price1 and p.price<:price2 and p.buyer is null order by p.price ASC")
    List<ProductOneDTO> getProductBy(BigDecimal price1, BigDecimal price2);

}
