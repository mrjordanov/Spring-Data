package com.example.json_ex.product_shop.entities.users;

import com.example.json_ex.product_shop.entities.products.ExportSoldProductDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldProductDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name ="product")
    private List<ExportSoldProductDTO> sellingItems;

    public ExportUserWithSoldProductDTO() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellingItems(List<ExportSoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
