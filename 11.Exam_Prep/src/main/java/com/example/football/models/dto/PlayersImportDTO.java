package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersImportDTO {

    @XmlElement(name = "player")
    private List<PlayerDTOs> playerDTOsList;

    public PlayersImportDTO() {
    }

    public List<PlayerDTOs> getPlayerDTOsList() {
        return playerDTOsList;
    }
}
