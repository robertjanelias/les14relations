package com.example.les14relations.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private short sp;

    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getSp() {
        return sp;
    }

    public void setSp(short sp) {
        this.sp = sp;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }
}
