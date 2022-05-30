package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.product_shop.entities.products.ProductOneOutDTO;


import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
 //  List<ProductOutDTO> getProductWithPriceAndFullNameOfTheSeller(BigDecimal value1, BigDecimal value2);

   List<ProductOneOutDTO> getProductWithPriceAndFullNameOfTheSeller(BigDecimal value1, BigDecimal value2);

    List<CategoryStatsDTO> getCategoryStatistic();
}
