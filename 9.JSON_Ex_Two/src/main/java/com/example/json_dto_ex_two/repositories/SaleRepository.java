package com.example.json_dto_ex_two.repositories;

import com.example.json_dto_ex_two.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
}
