package com.example.json_ex.product_shop.services;

import com.example.json_ex.product_shop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.product_shop.entities.products.ProductOneOutDTO;
import com.example.json_ex.product_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductOneOutDTO> getProductWithPriceAndFullNameOfTheSeller(BigDecimal value1, BigDecimal value2) {
        return productRepository.findProductByPriceBetweenAndBuyerIsNullOrderByPriceAsc(value1, value2);
    }

    @Override
    public List<CategoryStatsDTO> getCategoryStatistic() {
        return productRepository.getCategoryStats();

    }
}
