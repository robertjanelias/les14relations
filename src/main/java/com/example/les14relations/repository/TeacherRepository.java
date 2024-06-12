package com.example.les14relations.repository;

import com.example.les14relations.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByLastNameContainingIgnoreCase(String lname);
}
