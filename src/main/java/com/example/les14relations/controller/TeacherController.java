package com.example.les14relations.controller;

import com.example.les14relations.dto.TeacherDto;
import com.example.les14relations.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<List<Teacher>> getAllTeachers() {
//        return ResponseEntity.ok(repos.findAll());
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Teacher>> getTeachersByLastName(@RequestParam String lname) {
//        return ResponseEntity.ok(repos.findByLastNameContainingIgnoreCase(lname));
//    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        try {
            if (br.hasFieldErrors()) {
                StringBuilder sb = new StringBuilder();
                for (FieldError fe : br.getFieldErrors()) {
                    sb.append(fe.getField());
                    sb.append(" : ");
                    sb.append(fe.getDefaultMessage());
                    sb.append("\n");
                }
                return ResponseEntity.badRequest().body(sb.toString());
            }
            teacherDto = service.createTeacher(teacherDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + teacherDto.id).toUriString());
            return ResponseEntity.created(uri).body(teacherDto);
        }
        catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Creation failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeacherById(id));
    }
}
