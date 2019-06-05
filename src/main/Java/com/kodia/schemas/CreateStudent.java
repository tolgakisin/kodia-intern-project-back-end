package com.kodia.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CreateStudent {
    private String name;
    @JsonProperty("started_at")
    private Date startedAt;
    private int university;

    public CreateStudent() {
    }

    public CreateStudent(String name, Date startedAt, int university) {
        this.name = name;
        this.startedAt = startedAt;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public int getUniversity() {
        return university;
    }

    public void setUniversity(int university) {
        this.university = university;
    }
}
