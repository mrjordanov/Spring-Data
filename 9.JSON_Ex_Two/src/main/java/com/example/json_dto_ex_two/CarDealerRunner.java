package com.example.json_dto_ex_two;

import com.example.json_dto_ex_two.entities.dtoToQueries.CustomerOneDTO;
import com.example.json_dto_ex_two.entities.dtoToQueries.SupplierDTO;
import com.example.json_dto_ex_two.services.SupplierService;
import com.example.json_dto_ex_two.services.CarService;
import com.example.json_dto_ex_two.services.CustomerService;
import com.example.json_dto_ex_two.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarDealerRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    private final Gson gson;


    @Autowired
    public CarDealerRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run(String... args) throws Exception {
        // seedService.seedSuppliers();
        // seedService.seedParts();
        // seedService.seedCars();
        //  seedService.seedCostumers();
        // seedService.seedSales();

       // firstQuery();
      // secondQuery();
        // thirdQuery();
        //  fourthQuery();
        //  fifthQuery();


    }

    private void fifthQuery() {
        customerService.getAllCustomersWithNumberOfBoughtCarsAndTheirPrice();
    }

    private void fourthQuery() {
        carService.carsWithTheirParts().stream().map(gson::toJson).forEach(System.out::println);
    }

    private void thirdQuery() {
        List<SupplierDTO> suppliers = supplierService.getSuppliersWhoAreNotImporters();
        suppliers.stream().map(gson::toJson).forEach(System.out::println);
    }

    private void secondQuery() {
        carService.getCarsFromToyota().stream().map(gson::toJson).forEach(System.out::println);
    }

    private void firstQuery() {
        List<CustomerOneDTO> allCustomers = customerService.getAllCustomersOrderedByBirthDate();
        allCustomers.stream().map(gson::toJson).forEach(System.out::println);
    }
}
