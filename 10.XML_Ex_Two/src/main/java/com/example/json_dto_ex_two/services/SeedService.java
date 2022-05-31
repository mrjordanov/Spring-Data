package com.example.json_dto_ex_two.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedSuppliers() throws FileNotFoundException, JAXBException;

    void seedParts() throws FileNotFoundException, JAXBException;

    void seedCars() throws FileNotFoundException, JAXBException;

    void seedCostumers() throws FileNotFoundException, JAXBException;

    void seedSales();
}
