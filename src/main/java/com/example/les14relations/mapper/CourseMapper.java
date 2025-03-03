package com.example.les14relations.mapper;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
import com.example.les14relations.model.Course;

public class CourseMapper {

    public static Course toEntity(CourseInputDto courseInputDto){
        Course course = new Course();
        course.setTitle(courseInputDto.title);
        course.setSp(courseInputDto.sp);

        return course;
    }

    public static CourseOutputDto toDto(Course course) {
        CourseOutputDto courseOutputDto = new CourseOutputDto();
        courseOutputDto.id = course.getId();
        courseOutputDto.title = course.getTitle();
        courseOutputDto.sp = course.getSp();

        courseOutputDto.teacherDtoList =
                course.getTeachers()
                .stream()
                .map(TeacherMapper::toDto)
                .toList();

        return courseOutputDto;
    }
}
