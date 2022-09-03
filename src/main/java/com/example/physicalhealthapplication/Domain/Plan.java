package com.example.physicalhealthapplication.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    private String category;

    private Integer days;

    private Time timer;

    @Lob
    private String image;


    public String getPlanName () {
        return planName;
    }

    public void setPlanName (String planName) {
        this.planName = planName;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public Integer getDays () {
        return days;
    }

    public void setDays (Integer days) {
        this.days = days;
    }

    public Time getTimer () {
        return timer;
    }

    public void setTimer (Time timer) {
        this.timer = timer;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }


    public Plan (String planName, String category, Integer days, Time timer) {
        this.planName = planName;
        this.category = category;
        this.days = days;
        this.timer = timer;
    }

    public Plan () {
    }

}
