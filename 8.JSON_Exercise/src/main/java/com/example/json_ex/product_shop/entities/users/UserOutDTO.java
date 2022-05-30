package com.example.json_ex.product_shop.entities.users;

import com.example.json_ex.product_shop.entities.products.ProductTwoDTO;

import java.util.List;


public class UserOutDTO {

    private String firstName;
    private String lastName;

    private List<ProductTwoDTO> itemsBought;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductTwoDTO> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<ProductTwoDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
