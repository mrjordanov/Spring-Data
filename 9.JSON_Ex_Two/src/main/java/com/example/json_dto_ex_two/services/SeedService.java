package com.example.json_dto_ex_two.services;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedSuppliers() throws FileNotFoundException;

    void seedParts() throws FileNotFoundException;

    void seedCars() throws FileNotFoundException;

    void seedCostumers() throws FileNotFoundException;

    void seedSales();
}
