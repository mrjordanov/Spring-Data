package com.example.json_dto_ex_two.services;

import com.example.json_dto_ex_two.entities.*;
import com.example.json_dto_ex_two.entities.dtoToSeed.*;
import com.example.json_dto_ex_two.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {

    private static final String SUPPLIERS_PATH = "src\\main\\resources\\XMLtoImportInDB\\suppliers.xml";
    private static final String PARTS_PATH = "src\\main\\resources\\XMLtoImportInDB\\parts.xml";
    private static final String CARS_PATH = "src\\main\\resources\\XMLtoImportInDB\\cars.xml";
    private static final String CUSTOMER_PATH = "src\\main\\resources\\XMLtoImportInDB\\customers.xml";

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    private final ModelMapper mapper;


    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public void seedSuppliers() throws FileNotFoundException, JAXBException {
        FileReader dirToSuppliers = new FileReader(SUPPLIERS_PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(SuppliersToSeedDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SuppliersToSeedDTO unmarshal = (SuppliersToSeedDTO) unmarshaller.unmarshal(dirToSuppliers);

        List<Supplier> supplierList = unmarshal.getSupplierImportDTOList().stream().map(supplier -> mapper.map(supplier, Supplier.class)).toList();

        supplierRepository.saveAll(supplierList);
    }

    @Override
    public void seedParts() throws FileNotFoundException, JAXBException {
        FileReader dirToParts = new FileReader(PARTS_PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(PartsToSeedDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        PartsToSeedDTO unmarshal = (PartsToSeedDTO) unmarshaller.unmarshal(dirToParts);
        List<Part> partList = unmarshal.getPartToImportDTOList()
                .stream()
                .map(part -> mapper.map(part, Part.class))
                .map(this::setRandomSupplier).toList();

        partRepository.saveAll(partList);
    }

    @Override
    public void seedCars() throws FileNotFoundException, JAXBException {
        FileReader dirToCars = new FileReader(CARS_PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(CarsToSeedDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CarsToSeedDTO unmarshal = (CarsToSeedDTO) unmarshaller.unmarshal(dirToCars);


        List<Car> collect = unmarshal.getCarImportDTOList().stream()
                .map(car -> mapper.map(car, Car.class))
                .map(this::setRandomParts).toList();

        carRepository.saveAll(collect);
    }

    @Override
    public void seedCostumers() throws FileNotFoundException, JAXBException {
        FileReader pathToCustomers = new FileReader(CUSTOMER_PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomersToSeedDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CustomersToSeedDTO unmarshal = (CustomersToSeedDTO) unmarshaller.unmarshal(pathToCustomers);
        List<Customer> customerList = unmarshal.getCustomerToImportDTOList().stream().map(customer -> mapper.map(customer, Customer.class)).collect(Collectors.toList());

        customerRepository.saveAll(customerList);
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
            saleRepository.save(sale);
        }

    }

    private Car setRandomParts(Car car) {
        Random random = new Random();
        long partsCategoriesCount = partRepository.count();

        Set<Part> partSet = new HashSet<>();

        int randomlyGeneratedCategoriesCount = random.nextInt(20 - 10);

        for (int i = 0; i <= randomlyGeneratedCategoriesCount + 10; i++) {
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
