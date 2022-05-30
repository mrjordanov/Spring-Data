package com.example.json_ex.product_shop.entities.products;

import java.util.List;

public class ExportSoldProductsDTO {

    private int count;

    private List<ExportNamePriceDTO> products;

    public ExportSoldProductsDTO(List<ExportNamePriceDTO> products) {
        this.products = products;
        this.count=products.size();

    }

    public int getCount() {
        return count;
    }

    public List<ExportNamePriceDTO> getProducts() {
        return products;
    }
}
