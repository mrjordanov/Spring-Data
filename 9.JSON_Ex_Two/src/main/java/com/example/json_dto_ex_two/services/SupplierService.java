package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getSuppliersWhoAreNotImporters();
}
