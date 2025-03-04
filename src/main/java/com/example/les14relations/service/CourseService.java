package com.example.les14relations.service;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
import com.example.les14relations.mapper.CourseMapper;
import com.example.les14relations.model.Course;
import com.example.les14relations.model.Teacher;
import com.example.les14relations.repository.CourseRepository;
import com.example.les14relations.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepos, TeacherRepository teacherRepos) {
        this.courseRepository = courseRepos;
        this.teacherRepository = teacherRepos;
    }
    public CourseOutputDto createCourse(CourseInputDto courseInputDto) {

        Course course = CourseMapper.toEntity(courseInputDto);
        courseRepository.save(course);

        return CourseMapper.toDto(course);
    }

    public List<CourseOutputDto> getAllCourses() {
        List<Course> courses = this.courseRepository.findAll();

        return courses
                .stream()
                .map(CourseMapper::toDto)
                .toList();
    }

    public void assignTeacher(Long courseId, Long teacherId) {
        // only happy flow now
        Course course = this.courseRepository.findById(courseId).get();
        Teacher teacher = this.teacherRepository.findById(teacherId).get();

        course.getTeachers().add(teacher);
        this.courseRepository.save(course);
    }

    public void deleteCourse(long courseId) {
        this.courseRepository.deleteById(courseId);
    }
}