package com.kodia.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UniversityForStudentDetailItem {
    private int id;
    private String name;
    @JsonProperty("founded_at")
    private Date foundedAt;
    private String type;

    public UniversityForStudentDetailItem() {
    }

    public UniversityForStudentDetailItem(int id, String name, Date foundedAt, String type) {
        this.id = id;
        this.name = name;
        this.foundedAt = foundedAt;
        this.type = type;
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

    public Date getFoundedAt() {
        return foundedAt;
    }

    public void setFoundedAt(Date foundedAt) {
        this.foundedAt = foundedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
