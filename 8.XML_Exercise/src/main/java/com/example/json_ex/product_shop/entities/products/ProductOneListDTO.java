package com.example.json_ex.product_shop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductOneListDTO {

    @XmlElement(name = "product")
    private List<ProductOneDTO> list;

    public ProductOneListDTO() {
    }


    public void setList(List<ProductOneDTO> list) {
        this.list = list;
    }
}
