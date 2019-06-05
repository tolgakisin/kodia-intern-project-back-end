package com.kodia.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class StudentDetailItem {
    private int id;
    private String name;
    @JsonProperty("started_at")
    private Date startedAt;
    private UniversityForStudentDetailItem university;

    public StudentDetailItem() {
    }

    public StudentDetailItem(int id, String name, Date startedAt, UniversityForStudentDetailItem university) {
        this.id = id;
        this.name = name;
        this.startedAt = startedAt;
        this.university = university;
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

    public UniversityForStudentDetailItem getUniversity() {
        return university;
    }

    public void setUniversity(UniversityForStudentDetailItem university) {
        this.university = university;
    }
}
