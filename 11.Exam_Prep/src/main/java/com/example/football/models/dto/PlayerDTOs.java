package com.example.football.models.dto;

import com.example.football.models.entity.Position;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDTOs {

    @XmlElement(name = "first-name")
    @Size(min=3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min=3)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private Position position;

    @XmlElement(name = "town")
    private TownDTO town;

    @XmlElement(name = "team")
    private TeamDTO team;

    @XmlElement(name = "stat")
    private StatONEDTO stat;

    public PlayerDTOs() {
    }

    public StatONEDTO getStat() {
        return stat;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public TownDTO getTown() {
        return town;
    }

    public TeamDTO getTeam() {
        return team;
    }


   /* public boolean isValid() {
        if (firstName.length() <= 2) {
            return false;
        }
        if (lastName.length() <= 2) {
            return false;
        }
        if (!isEmailValid(email)) {
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {

            int count = 0;
            for (int i = 0; i < email.length(); i++) {
                char cur = email.charAt(i);
                if (cur == '@') {
                    count++;
                }
            }
            if (count == 1) {
                if (email.indexOf("@") < email.lastIndexOf(".")) {
                    return true;
                }
            }
        }
        return false;
    }*/


}
