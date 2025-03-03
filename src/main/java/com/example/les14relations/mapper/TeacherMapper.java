package com.example.les14relations.mapper;

import com.example.les14relations.dto.TeacherDto;
import com.example.les14relations.model.Teacher;

public class TeacherMapper {

    public static Teacher toEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setEmail(teacherDto.email);

        return teacher;
    }

    public static TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = teacher.getId();
        teacherDto.firstName = teacher.getFirstName();
        teacherDto.lastName = teacher.getLastName();
        teacherDto.dob = teacher.getDob();
        teacherDto.email = teacher.getEmail();

        return teacherDto;
    }
}
