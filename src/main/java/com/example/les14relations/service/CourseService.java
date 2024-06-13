package com.example.les14relations.service;

import com.example.les14relations.dto.CourseInputDto;
import com.example.les14relations.dto.CourseOutputDto;
import com.example.les14relations.dto.TeacherDto;
import com.example.les14relations.model.Course;
import com.example.les14relations.model.Teacher;
import com.example.les14relations.repository.CourseRepository;
import com.example.les14relations.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CourseOutputDto> getAllCourses() {
        List<Course> courses = this.courseRepository.findAll();
        List<CourseOutputDto> courseOutputDtos = new ArrayList<>();

        for (Course course : courses) {
            CourseOutputDto courseOutputDto = new CourseOutputDto();

            courseOutputDto.id = course.getId();
            courseOutputDto.title = course.getTitle();
            courseOutputDto.sp  = course.getSp();

            for (Teacher teacher : course.getTeachers()) {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.id = teacher.getId();
                teacherDto.firstName = teacher.getFirstName();
                teacherDto.lastName = teacher.getLastName();
                teacherDto.dob = teacher.getDob();
                teacherDto.email = teacher.getEmail();
                courseOutputDto.teacherDtoList.add(teacherDto);
            }

            courseOutputDtos.add(courseOutputDto);
        }
        return courseOutputDtos;
    }

    public void assignTeacher(Long courseId, Long teacherId) {
        Course course = this.courseRepository.findById(courseId).get();
        Teacher teacher = this.teacherRepository.findById(teacherId).get();

        course.getTeachers().add(teacher);
        this.courseRepository.save(course);
    }
}