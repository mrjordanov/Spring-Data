package com.example.json_ex.product_shop.entities.users;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDTO {

    @XmlAttribute(name ="first-name")
    private String firstName;

    @XmlAttribute(name ="last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    public UserImportDTO() {
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
