package com.example.json_ex.product_shop.entities.users;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellersDTO {

    @XmlElement(name = "user")
    List<ExportUserWithSoldProductDTO> users;

    public ExportSellersDTO() {
    }

    public ExportSellersDTO(List<ExportUserWithSoldProductDTO> users) {
        this.users = users;
    }


}
