package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamsDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;


@Service
public class TeamServiceImpl implements TeamService {

    private final Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

    private final TeamRepository teamRepository;

    private final TownRepository townRepository;

    private final Gson gson;

    private final ModelMapper mapper;


    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {

        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        String dir = readTeamsFileContent();

        StringBuilder sb = new StringBuilder();

        ImportTeamsDTO[] importTeamsDTO = gson.fromJson(dir, ImportTeamsDTO[].class);

        Arrays.stream(importTeamsDTO).forEach(team -> {

            if (team.isValid()) {

                Optional<Team> teamFromDB = teamRepository.findByName(team.getName());

                if (teamFromDB.isPresent()) {
                    sb.append("Invalid Team").append("\n");
                } else {
                    Team teamToAddInDB = mapper.map(team, Team.class);

                    Town town = townRepository.findByName(team.getTownName());

                    teamToAddInDB.setTown(town);
                    teamRepository.save(teamToAddInDB);
                    sb.append("Successfully added Team ").append(team.getName())
                            .append("-").append(team.getFanBase()).append("\n");
                }

            } else {
                sb.append("Invalid Team").append("\n");
            }

        });

        return sb.toString().trim();
    }
}
