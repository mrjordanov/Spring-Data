package com.example.json_ex.product_shop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameDTO {

        private String name;

    public CategoryNameDTO() {
    }

    public String getName() {
        return name;
    }
}
