package com.example.json_ex.product_shop.entities.products;


import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ProductOutDTO {

    String getName();

    BigDecimal getPrice();

    @Value("#{target.seller.firstName+ ' '+ target.seller.lastName}")
     String getSeller();


}
