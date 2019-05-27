package com.kodia.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "universities")
public class University implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;
    @Column(name = "api_id", nullable = false, unique = true)
    private int apiId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String city;
    @Column(name = "web_page", nullable = false)
    private String webPage;
    @Column(nullable = false)
    private String type;
    @Column(name = "founded_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date foundedAt;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private Date updatedAt;
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> studentList;

}
