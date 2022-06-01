package com.example.football.service.impl;

import com.example.football.models.dto.StatsImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Service
public class StatServiceImpl implements StatService {

    private final Path path = Path.of("src", "main", "resources", "files", "xml", "stats.xml");

    private final StatRepository statRepository;

    private final JAXBContext jaxb;

    private final ModelMapper mapper;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;
        this.jaxb = JAXBContext.newInstance(StatsImportDTO.class);
        this.mapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importStats() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        FileReader reader = new FileReader("src\\main\\resources\\files\\xml\\stats.xml");

        Unmarshaller unmarshaller = jaxb.createUnmarshaller();

        StatsImportDTO dtos = (StatsImportDTO) unmarshaller.unmarshal(reader);

        dtos.getStatDTOList().forEach(s -> {

            if (s.isValid()) {

                Optional<Stat> statFromDB = statRepository.findByPassingAndShootingAndEndurance(s.getPassing(), s.getShooting(), s.getEndurance());

                if (statFromDB.isPresent()) {
                    sb.append("Invalid Stat").append("\n");

                } else {
                    Stat statToAddInDB = mapper.map(s, Stat.class);
                    statRepository.save(statToAddInDB);
                    sb.append("Successfully imported Stat ").append(s.getPassing())
                            .append("-").append(s.getShooting()).append("-").append(s.getEndurance()).append("\n");
                }
            } else {
                sb.append("Invalid Stat").append("\n");
            }

        });

        return sb.toString().trim();
    }
}
