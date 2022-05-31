package com.example.json_dto_ex_two;

import com.example.json_dto_ex_two.entities.dtoToQueries.*;
import com.example.json_dto_ex_two.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


@Component
public class CarDealerRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;
    private final SaleService saleService;

    @Autowired
    public CarDealerRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService, CustomerService customerService1, CarService carService1, SupplierService supplierService1, SaleService saleService) {
        this.seedService = seedService;
        this.customerService = customerService1;
        this.carService = carService1;
        this.supplierService = supplierService1;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //  seedService.seedSuppliers();
        // seedService.seedParts();
        // seedService.seedCars();
        //   seedService.seedCostumers();
        //  seedService.seedSales();
        // firstQuery();
        // secondQuery();
        // thirdQuery();
        //  fourthQuery();
       //fifthQuery();
      //  sixthQuery();
    }

    private void sixthQuery() throws JAXBException {
        SalesOutDTO salesOutDTO = saleService.salesWithAppliedDiscount();
        JAXBContext jaxbContext=JAXBContext.newInstance(SalesOutDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(salesOutDTO,new File("src\\main\\resources\\XMLOutPuts\\sales-discounts.xml"));
    }

    private void fifthQuery() throws JAXBException {
        CustomerOutDTOForFifth allCustomersForFifthQuery = customerService.getAllCustomersForFifthQuery();
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerOutDTOForFifth.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(allCustomersForFifthQuery,new File("src\\main\\resources\\XMLOutPuts\\total-sales-by-customer.xml"));
    }

    private void fourthQuery() throws JAXBException {
        CarsOutPutForFourthQuery allCarsForFourthQuery = carService.getAllCarsForFourthQuery();
        JAXBContext jaxbContext = JAXBContext.newInstance(CarsOutPutForFourthQuery.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(allCarsForFourthQuery, new File("src\\main\\resources\\XMLOutPuts\\cars-and-parts.xml"));
    }

    private void thirdQuery() throws JAXBException {
        SuppliersOutDTO localSuppliers = supplierService.getLocalSuppliers();
        JAXBContext jaxbContext = JAXBContext.newInstance(SuppliersOutDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(localSuppliers, new File("src\\main\\resources\\XMLOutPuts\\local-suppliers.xml"));
    }

    private void secondQuery() throws JAXBException {
        CarsOutPutForSecondQueryDTO cars = carService.getAllCarsForSecondQuery();
        JAXBContext jaxbContext = JAXBContext.newInstance(CarsOutPutForSecondQueryDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cars, new File("src\\main\\resources\\XMLOutPuts\\toyota-cars.xml"));
    }

    private void firstQuery() throws JAXBException {
        CustomersForFirstQuery allCustomers = customerService.getAllCustomers();
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomersForFirstQuery.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(allCustomers, new File("src\\main\\resources\\XMLOutPuts\\ordered-customers.xml"));
    }


}
