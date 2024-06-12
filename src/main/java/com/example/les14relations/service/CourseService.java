package com.example.les14relations.service;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
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
    public CourseOutputDto createCourse(CourseInputDto courseInputDto) {
        // create course entity object
        Course course = new Course();
        course.setTitle(courseInputDto.title);
        course.setSp(courseInputDto.sp);

        courseRepository.save(course);

        CourseOutputDto courseOutputDto = new CourseOutputDto();
        courseOutputDto.id = course.getId();
        courseOutputDto.title = course.getTitle();
        courseOutputDto.sp = course.getSp();

        return courseOutputDto;
    }
}