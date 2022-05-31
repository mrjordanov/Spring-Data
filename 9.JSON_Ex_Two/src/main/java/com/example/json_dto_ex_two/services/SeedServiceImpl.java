package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.*;
import com.example.json_dto_ex_two.entities.dtoToSeed.CarsToSeedDTO;
import com.example.json_dto_ex_two.entities.dtoToSeed.CustomerToSeedDTO;
import com.example.json_dto_ex_two.entities.dtoToSeed.PartsToSeedDTO;
import com.example.json_dto_ex_two.entities.dtoToSeed.SuppliersToSeedDTO;
import com.example.json_dto_ex_two.repositories.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String SUPPLIERS_PATH = "src\\main\\resources\\CarDealerInfo\\suppliers.json";
    private static final String PARTS_PATH = "src\\main\\resources\\CarDealerInfo\\parts.json";
    private static final String CARS_PATH = "src\\main\\resources\\CarDealerInfo\\cars.json";
    private static final String CUSTOMER_PATH = "src\\main\\resources\\CarDealerInfo\\customers.json";

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mapper = new ModelMapper();
    }


    @Override
    public void seedParts() throws FileNotFoundException {
        FileReader dirToParts = new FileReader(PARTS_PATH);
        PartsToSeedDTO[] partsToSeeDTO = gson.fromJson(dirToParts, PartsToSeedDTO[].class);

        List<Part> partList = Arrays.stream(partsToSeeDTO)
                .map(part -> mapper.map(part, Part.class))
                .map(this::setRandomSupplier).collect(Collectors.toList());

        partRepository.saveAll(partList);
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        FileReader dirToCars = new FileReader(CARS_PATH);
        CarsToSeedDTO[] carsToSeedDTOS = gson.fromJson(dirToCars, CarsToSeedDTO[].class);
        List<Car> cars = Arrays.stream(carsToSeedDTOS)
                .map(car -> mapper.map(car, Car.class))
                .map(this::setRandomParts).collect(Collectors.toList());

        carRepository.saveAll(cars);
    }

    @Override
    public void seedCostumers() throws FileNotFoundException {
        FileReader pathToCustomers = new FileReader(CUSTOMER_PATH);
        CustomerToSeedDTO[] customerToSeedDTOS = gson.fromJson(pathToCustomers, CustomerToSeedDTO[].class);

        //!!!  TypeMap<CustomerToSeedDTO,Customer> customerDate=mapper.createTypeMap()

        List<Customer> customers = Arrays.stream(customerToSeedDTOS).map(customer -> mapper.map(customer, Customer.class))
                .collect(Collectors.toList());

        customerRepository.saveAll(customers);
    }

    @Override
    public void seedSales() {
        Set<Sale> sales = new HashSet<>();
        List<Double> typeOfDiscount = Arrays.asList(0.0, 0.05, 0.1, 0.15, 0.20, 0.3, 0.4, 0.5);
        int numberOfDiscount = typeOfDiscount.size();


        long countOfCars = carRepository.count();
        long countOfCustomers = customerRepository.count();
        for (int i = 0; i < countOfCars; i++) {

            Sale sale = new Sale();
            int randomCarId = new Random().nextInt((int) countOfCars) + 1;
            Optional<Car> carById = carRepository.findById(randomCarId);
            sale.setCar(carById.get());

            int customerById = new Random().nextInt((int) countOfCustomers) + 1;
            Optional<Customer> customer = customerRepository.findById(customerById);
            sale.setCustomer(customer.get());

            double discount = typeOfDiscount.get(new Random().nextInt(numberOfDiscount));
            sale.setDiscount(discount);

            sales.add(sale);
        }

        saleRepository.saveAll(sales);

    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        FileReader dirToSuppliers = new FileReader(SUPPLIERS_PATH);
        SuppliersToSeedDTO[] suppliers = gson.fromJson(dirToSuppliers, SuppliersToSeedDTO[].class);

        List<Supplier> suppliersList = Arrays.stream(suppliers)
                .map(object -> mapper.map(object, Supplier.class)).collect(Collectors.toList());

        supplierRepository.saveAll(suppliersList);
    }

    private Car setRandomParts(Car car) {
        Random random = new Random();
        long partsCategoriesCount = partRepository.count();

        Set<Part> partSet = new HashSet<>();

        for (int i = 0; i <= random.nextInt(5 - 2) + 3; i++) {
            int randomId = new Random().nextInt((int) partsCategoriesCount) + 1;
            Optional<Part> part = partRepository.findById(randomId);
            partSet.add(part.get());
        }

        car.setPart(partSet);

        return car;
    }

    private Part setRandomSupplier(Part part) {
        long numberOfSuppliers = supplierRepository.count();
        int randomId = new Random().nextInt((int) numberOfSuppliers) + 1;
        Optional<Supplier> supplier = supplierRepository.findById(randomId);

        part.setSupplier(supplier.get());

        return part;
    }
}
