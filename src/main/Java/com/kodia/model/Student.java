package com.kodia.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "university_id", referencedColumnName = "id", nullable = false)
    private University university;
    @Column(nullable = false)
    private String name;
    @Column(name = "started_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date startedAt;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
