package com.example.json_ex.product_shop.entities.users;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersListImportDTO {

    @XmlElement(name = "user")
    private List<UserImportDTO> userImportDTOList;

    public UsersListImportDTO() {
    }

    public List<UserImportDTO> getUserImportDTOList() {
        return userImportDTOList;
    }
}
