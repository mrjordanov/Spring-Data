package com.example.json_ex.product_shop.entities.categories;


import java.io.Serializable;
import java.math.BigDecimal;


public class CategoryStatsDTO implements Serializable {

    private String category;

    private long productCount;

    private double averagePRice;

    private BigDecimal totalRevenue;

    public CategoryStatsDTO(String category, long productCount, double averagePRice, BigDecimal totalRevenue) {
        this.category = category;
        this.productCount = productCount;
        this.averagePRice = averagePRice;
        this.totalRevenue = totalRevenue;
    }



    public String getCategory() {
        return category;
    }

    public long getProductCount() {
        return productCount;
    }

    public double getAveragePRice() {
        return averagePRice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
