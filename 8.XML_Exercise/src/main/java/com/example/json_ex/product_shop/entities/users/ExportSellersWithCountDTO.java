package com.example.json_ex.product_shop.entities.users;


import java.util.List;

public class ExportSellersWithCountDTO {

    private int usersCount;

    private List<ExportUserWithSoldCountDTO> users;

    public ExportSellersWithCountDTO(List<ExportUserWithSoldCountDTO> users) {
        this.users = users;
        this.usersCount=users.size();
    }
}
