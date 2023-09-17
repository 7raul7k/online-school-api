package ro.myclass.onlineschoolapi.course.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.professor.model.Professor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class CourseRepoTest {

    @Autowired
    public CourseRepo courseRepo;

    @BeforeEach
    public void clean() {
        courseRepo.deleteAll();
    }

    @Test
    public void getAllCourses() {

        Course course = Course.builder().name("Matematica").department("Matematica").build();
        Course course1 = Course.builder().name("Romana").department("Romana").build();
        Course course2 = Course.builder().name("Istorie").department("Istorie").build();
        Course course3 = Course.builder().name("Geografie").department("Geografie").build();
        courseRepo.save(course);
        courseRepo.save(course1);
        courseRepo.save(course2);
        courseRepo.save(course3);

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        assertEquals(courseList, courseRepo.getAllCourses());
    }

    @Test
    public void getCourseByName() {
        Course course = Course.builder().name("Matematica").department("Matematica").build();
        courseRepo.save(course);
        assertEquals(course, courseRepo.getCourseByName("Matematica").get());
    }

    @Test
    public void getCourseByNameAndDepartment() {
        Course course = Course.builder().name("Matematica").department("Matematica").build();
        courseRepo.save(course);
        assertEquals(course, courseRepo.getCourseByNameAndDepartment("Matematica","Matematica").get());
    }

    @Test
    public void getCourseByDepartment() {
        Course course = Course.builder().name("Matematica").department("Matematica").build();
        courseRepo.save(course);
        assertEquals(course, courseRepo.getCourseByDepartment("Matematica").get(0));
    }

    @Test
    public void getCourseById() {
        Course course = Course.builder().name("Matematica").department("Matematica").build();
        courseRepo.save(course);
        assertEquals(course, courseRepo.getCourseById(course.getId()).get(0));
    }


}