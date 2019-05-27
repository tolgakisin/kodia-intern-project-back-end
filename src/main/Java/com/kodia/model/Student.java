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
}
