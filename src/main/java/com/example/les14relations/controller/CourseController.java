package com.example.les14relations.controller;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
import com.example.les14relations.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
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
}
