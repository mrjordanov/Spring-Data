package com.example.json_dto_ex_two.repositories;

import com.example.json_dto_ex_two.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Integer> {
}
