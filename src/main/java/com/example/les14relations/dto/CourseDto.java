package com.example.les14relations.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {
    public Long id;
    public String title;
    public short sp;
    public List<TeacherDto> teacherDtoList = new ArrayList<>();
}
