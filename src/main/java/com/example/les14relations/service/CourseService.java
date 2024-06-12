package com.example.les14relations.service;

import com.example.les14relations.dto.CourseDto;
import com.example.les14relations.model.Course;
import com.example.les14relations.repository.CourseRepository;
import com.example.les14relations.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepos, TeacherRepository teacherRepos) {
        this.courseRepository = courseRepos;
        this.teacherRepository = teacherRepos;
    }
    public CourseDto createCourse(CourseDto courseDto) {
        // create course entity object
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);

        courseRepository.save(course);
        courseDto.id = course.getId();
        return courseDto;
    }
}