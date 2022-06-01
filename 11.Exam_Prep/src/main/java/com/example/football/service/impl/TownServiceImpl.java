package com.example.football.service.impl;

import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;


@Service
public class TownServiceImpl implements TownService {

    private final Path path = Paths.get("src", "main", "resources", "files", "json", "towns.json");

    private final TownRepository townRepository;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean areImported() {
        return townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws FileNotFoundException {
        String dirToTowns = "src\\main\\resources\\files\\json\\towns.json";
        FileReader reader = new FileReader(dirToTowns);

        StringBuilder sb = new StringBuilder();

        Town[] towns = gson.fromJson(reader, Town[].class);
        Arrays.stream(towns).forEach(town -> {

            if (town.isValid()) {
                Optional<Town> townByName = townRepository.findTownByName(town.getName());

                if (townByName.isPresent()) {
                    sb.append("Invalid Town").append("\n");
                } else {
                    townRepository.save(town);
                    sb.append("Successfully imported Town ").append(town.getName())
                            .append("-").append(town.getPopulation()).append("\n");
                }
            } else {
                sb.append("Invalid Town").append("\n");
            }
        });

        return sb.toString().trim();
    }
}
