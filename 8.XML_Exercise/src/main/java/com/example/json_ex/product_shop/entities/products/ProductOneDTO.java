package com.example.json_ex.product_shop.entities.products;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductOneDTO implements Serializable {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;
    @XmlAttribute
    private String seller;

    public ProductOneDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;
        if (firstName == null) {
            this.seller = lastName;
        } else {
            this.seller = firstName + ' ' + lastName;
        }
    }

    public ProductOneDTO() {
    }

}
