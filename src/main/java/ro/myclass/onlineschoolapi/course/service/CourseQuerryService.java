package ro.myclass.onlineschoolapi.course.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.model.Course;

import java.util.List;

@Service
public interface CourseQuerryService  {

    List<Course> getAllCourses();
    Course getCourseById(int id);
    Course getCourseByName(String name);
    Course getCourseByNameAndDepartment(String name, String department);

    List<Course> getCourseByDepartment(String department);

}
