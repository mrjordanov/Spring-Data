package com.example.json_ex.product_shop.repositories;

import com.example.json_ex.product_shop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.product_shop.entities.products.Product;
import com.example.json_ex.product_shop.entities.products.ProductOneOutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.example.json_ex.product_shop.entities.products.ProductOneOutDTO(" +
            "p.name,p.price,p.seller.firstName,p.seller.lastName) from Product p" +
            " where p.price >:price and p.price <:price2 and p.buyer is null order by p.price ASC")
    List<ProductOneOutDTO> findProductByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal price, BigDecimal price2);

    @Query("select new com.example.json_ex.product_shop.entities.categories.CategoryStatsDTO(c.name, count(p) ,avg (p.price),sum(p.price)) from Product p" +
            " JOIN p.categories c GROUP BY c")
    List<CategoryStatsDTO> getCategoryStats();
}
