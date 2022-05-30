package com.example.json_ex.product_shop.services;


import com.example.json_ex.product_shop.entities.products.ProductOneDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductOneDTO> getProductWithPriceAndFullNameOfTheSeller(BigDecimal bigDecimal, BigDecimal bigDecimal1);
}
