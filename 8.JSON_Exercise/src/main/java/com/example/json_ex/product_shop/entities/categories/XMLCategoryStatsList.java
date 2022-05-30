package com.example.json_ex.product_shop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories-stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsList {

    @XmlElementWrapper(name = "categories")
  //  @XmlElement(name="cat")
    private List<XMLCategoryStatsDTO> stats;

    public XMLCategoryStatsList() {
    }

    public XMLCategoryStatsList(List<XMLCategoryStatsDTO> stats) {
        this.stats = stats;
    }
}
