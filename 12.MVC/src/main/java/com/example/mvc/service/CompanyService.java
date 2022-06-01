package com.example.mvc.service;

import com.example.mvc.models.Company;
import com.example.mvc.models.dto.CompaniesDTO;
import com.example.mvc.models.dto.ImportCompaniesDTO;
import com.example.mvc.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;


    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean areImported() {
        return companyRepository.count()>0;
    }

    public String getCompaniesText() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xmls/companies.xml"));
    }


    public String importCompanies() throws JAXBException, FileNotFoundException {

        FileReader reader = new FileReader("src\\main\\resources\\files\\xmls\\companies.xml");

        JAXBContext jaxb = JAXBContext.newInstance(ImportCompaniesDTO.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        ImportCompaniesDTO unmarshal = (ImportCompaniesDTO) unmarshaller.unmarshal(reader);

     return    unmarshal.getCompaniesDTOList()
                .stream()
                .map(CompaniesDTO::getName)
                .map(Company::new)
                .map(c -> {

                    Optional<Company> optionalCompany = companyRepository.findByName(c.getName());

                    if (optionalCompany.isPresent()) {
                        return "Invalid company";
                    }
                    companyRepository.save(c);
                    return "Created Company - " + c.getName();
                }).collect(Collectors.joining("\n"));

    }
}
