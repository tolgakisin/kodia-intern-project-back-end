package com.kodia.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UniversityStudentListItem {
    private int id;
    private String name;
    @JsonProperty("started_at")
    private Date startedAt;

    public UniversityStudentListItem() {
    }

    public UniversityStudentListItem(int id, String name, Date startedAt) {
        this.id = id;
        this.name = name;
        this.startedAt = startedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
