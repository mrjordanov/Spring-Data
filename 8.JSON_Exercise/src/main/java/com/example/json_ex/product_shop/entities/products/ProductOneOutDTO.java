package com.example.json_ex.product_shop.entities.products;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductOneOutDTO implements Serializable {
    private String name;
    private BigDecimal price;
    private String seller;


    public ProductOneOutDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;

        if (firstName == null) {
            this.seller = lastName;
        } else {
            this.seller = firstName + ' ' + lastName;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }
}
