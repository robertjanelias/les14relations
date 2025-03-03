package com.example.les14relations.service;

import com.example.les14relations.dto.TeacherDto;
import com.example.les14relations.exception.ResourceNotFoundException;
import com.example.les14relations.mapper.TeacherMapper;
import com.example.les14relations.model.Teacher;
import com.example.les14relations.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.toEntity(teacherDto);

        this.repos.save(teacher);

        return TeacherMapper.toDto(teacher);
    }

    public TeacherDto getTeacherById(long id) {
        Teacher teacher = this.repos.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Teacher " + id + " not found"));

        return TeacherMapper.toDto(teacher);
    }
}
