package com.example.json_ex.product_shop.repositories;

import com.example.json_ex.product_shop.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category,Integer> {
}
