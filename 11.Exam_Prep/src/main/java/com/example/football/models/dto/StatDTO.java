package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatDTO {

    @XmlElement
    private float passing;
    @XmlElement
    private float shooting;
    @XmlElement
    private float endurance;

    public StatDTO() {
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public boolean isValid() {
        if (shooting <= 0) {
            return false;
        }
        if (passing <= 0) {
            return false;
        }
        if (endurance <= 0) {
            return false;
        }
        return true;
    }

}
