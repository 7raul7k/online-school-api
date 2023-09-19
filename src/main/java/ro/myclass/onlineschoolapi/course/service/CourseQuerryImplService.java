package ro.myclass.onlineschoolapi.course.service;


import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.exceptions.CourseNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQuerryImplService implements CourseQuerryService {

    private CourseRepo courseRepo;

    public CourseQuerryImplService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepo.getAllCourses();

        if (courseList.isEmpty()) {
            throw new ListEmptyException();
        }

        return courseList;

    }

    public Course getCourseById(int id) {
        Optional<Course> course = courseRepo.getCourseById(id);

        if (course.isEmpty()) {
            throw new CourseNotFoundException();
        }

        return course.get();
    }

    public Course getCourseByName(String name) {
        Optional<Course> course = courseRepo.getCourseByName(name);

        if (course.isEmpty()) {
            throw new CourseNotFoundException();
        }

        return course.get();
    }

    public Course getCourseByNameAndDepartment(String name, String department) {
        Optional<Course> course = courseRepo.getCourseByNameAndDepartment(name, department);

        if (course.isEmpty()) {
            throw new CourseNotFoundException();
        }

        return course.get();
    }



    public List<Course> getCourseByDepartment(String department) {
        List<Course> courseList = courseRepo.getCourseByDepartment(department);

        if (courseList.isEmpty()) {
            throw new ListEmptyException();
        }

        return courseList;
    }



}
