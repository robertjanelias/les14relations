package com.example.les14relations.controller;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
import com.example.les14relations.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseOutputDto>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<CourseOutputDto> createCourse(@Valid @RequestBody CourseInputDto courseInputDto) {
        // validation code is skipped

        CourseOutputDto courseOutputDto = service.createCourse(courseInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + courseOutputDto.id).toUriString());

        return ResponseEntity.created(uri).body(courseOutputDto);
    }

    @PostMapping("/{courseId}/assign")
    public ResponseEntity<String> assignTeacher(@PathVariable Long courseId,
                                                @RequestParam Long teacherId) {
        this.service.assignTeacher(courseId, teacherId);

        return ResponseEntity.ok("Course " + courseId + " linked with teacher " + teacherId);
    }
}
