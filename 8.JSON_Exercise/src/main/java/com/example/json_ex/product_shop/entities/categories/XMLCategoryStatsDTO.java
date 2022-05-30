package com.example.json_ex.product_shop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;


@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsDTO implements Serializable {

    @XmlElement(name = "name")
    private String category;

    private long productCount;

    private double averagePRice;

    private BigDecimal totalRevenue;

    public XMLCategoryStatsDTO() {
    }

    public XMLCategoryStatsDTO(CategoryStatsDTO other) {
        this.category = other.getCategory();
        this.productCount = other.getProductCount();
        this.averagePRice = other.getAveragePRice();
        this.totalRevenue = other.getTotalRevenue();
    }

    @Override
    public String toString() {
        return "XMLCategoryStatsDTO{" +
                "category='" + category + '\'' +
                ", productCount=" + productCount +
                ", averagePRice=" + averagePRice +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
