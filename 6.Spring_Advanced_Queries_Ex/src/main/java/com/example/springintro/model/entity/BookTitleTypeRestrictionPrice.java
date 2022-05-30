package com.example.springintro.model.entity;

import java.math.BigDecimal;

public interface BookTitleTypeRestrictionPrice {

    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
