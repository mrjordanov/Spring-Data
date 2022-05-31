package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO;
import com.example.json_dto_ex_two.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierDTO> getSuppliersWhoAreNotImporters() {
        return  supplierRepository.getAllByIsImporterFalse();
    }
}
