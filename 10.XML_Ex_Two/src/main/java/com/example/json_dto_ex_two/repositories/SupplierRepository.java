package com.example.json_dto_ex_two.repositories;

import com.example.json_dto_ex_two.entities.Supplier;
import com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

  /*  @Query("select new com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO(" +
            " s.id,s.name,s.parts.size) from Supplier s where s.isImporter=false")
    List<SupplierDTO> getAllByIsImporterFalse();*/


    @Query("select new com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO(" +
            "s.id,s.name,s.parts.size) from Supplier s where s.isImporter=false")
    List<SupplierDTO> getSupplierBy();
}
