package com.example.football.service.impl;

import com.example.football.models.dto.PlayerDTOs;
import com.example.football.models.dto.PlayersImportDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Service
public class PlayerServiceImpl implements PlayerService {

    private final Path path = Path.of("src", "main", "resources", "files", "xml", "players.xml");

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;

    private final JAXBContext jaxb;
    private final Validator validator;
    private final ModelMapper mapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
        this.jaxb = JAXBContext.newInstance(PlayersImportDTO.class);
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper = new ModelMapper();

        mapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {

        return Files.readString(path);
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        Unmarshaller unmarshaller = jaxb.createUnmarshaller();

        FileReader reader = new FileReader("src\\main\\resources\\files\\xml\\players.xml");
        PlayersImportDTO dtos = (PlayersImportDTO) unmarshaller.unmarshal(reader);

        dtos.getPlayerDTOsList().forEach(p -> {
            Set<ConstraintViolation<PlayerDTOs>> errors = validator.validate(p);

            if (!errors.isEmpty()) {
                sb.append("Invalid Player").append("\n");
            } else {
                Optional<Player> playerFromDB = playerRepository.findByEmail(p.getEmail());

                if (playerFromDB.isPresent()) {
                    sb.append("Invalid Player").append("\n");
                } else {

                    String townName = p.getTown().getName();
                    Town town = townRepository.findByName(townName);

                    String teamName = p.getTeam().getName();
                    Optional<Team> team = teamRepository.findByName(teamName);

                    long id = p.getStat().getId();
                    Optional<Stat> stat = statRepository.findById(id);

                    Player player = mapper.map(p, Player.class);

                    player.setTown(town);
                    player.setTeam(team.get());

                    player.setStat(stat.get());

                    playerRepository.save(player);
                    sb.append("Successfully imported Player ").append(p.getFirstName()).append(" ")
                            .append(p.getLastName())
                            .append("-").append(p.getPosition().toString()).append("\n");
                }
            }
        });

        //Вариант без Validation Annotations:
      /*
        dtos.getPlayerDTOsList().forEach(p -> {

            if (p.isValid()) {

                Optional<Player> playerFromDB = playerRepository.findByEmail(p.getEmail());

                if (playerFromDB.isPresent()) {
                    sb.append("Invalid Player").append("\n");
                } else {

                    String townName = p.getTown().getName();
                    Town town = townRepository.findByName(townName);

                    String teamName = p.getTeam().getName();
                    Optional<Team> team = teamRepository.findByName(teamName);

                    long id = p.getStat().getId();
                    Optional<Stat> stat = statRepository.findById(id);

                    Player player = mapper.map(p,Player.class);

                    player.setTown(town);
                    player.setTeam(team.get());

                    player.setStat(stat.get());

                    playerRepository.save(player);
                    sb.append("Successfully imported Player ").append(p.getFirstName()).append(" ")
                            .append(p.getLastName())
                            .append("-").append(p.getPosition().toString()).append("\n");
                }

            } else {
                sb.append("Invalid Player").append("\n");
            }
        });*/

        return sb.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        LocalDate localDate1 = LocalDate.parse("01-01-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate localDate2 = LocalDate.parse("01-01-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Player> players = playerRepository.findAllByBirthDateAfterAndBirthDateBeforeOrderByStat(localDate1, localDate2);

        //Вариант със заявка генерирана само с имена на полета
        /* SECOND VAR WITH ANOTHER QUERY!

       LocalDate before=LocalDate.of(2003,1,1);
       LocalDate after=LocalDate.of(1995,1,1);

        List<Player> playerSec = playerRepository.findAllByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after, before);
        String collect = playerSec.stream().map(Player::toString).collect(Collectors.joining(""));*/

        StringBuilder sb = new StringBuilder();

        players.forEach(p -> sb.append(p.toString()));

        return sb.toString().trim();
    }
}
