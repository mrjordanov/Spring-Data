package com.example.json_ex.product_shop.entities.categories;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDTO> categoryNameDTOList;

    public CategoryImportDTO() {
    }

    public CategoryImportDTO(List<CategoryNameDTO> categoryNameDTOList) {
        this.categoryNameDTOList = categoryNameDTOList;
    }

    public List<CategoryNameDTO> getCategoryNameDTOList() {
        return categoryNameDTOList;
    }
}
