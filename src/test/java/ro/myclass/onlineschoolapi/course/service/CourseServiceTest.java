package ro.myclass.onlineschoolapi.course.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.exceptions.CourseNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.CourseWasFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseCommandService courseCommandService = new CourseCommandImplService(courseRepo);

    @InjectMocks
    private CourseQuerryService courseQuerryService = new CourseQuerryImplService(courseRepo);

    @Captor
    private ArgumentCaptor<Course> argumentCaptor;

    @Test
    public void getAllCourses() {
     Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
     Course course1 = Course.builder().name("English").department("English").enrolment(new ArrayList<>()).build();
     Course course2 = Course.builder().name("French").department("French").enrolment(new ArrayList<>()).build();
     Course course3 = Course.builder().name("German").department("German").enrolment(new ArrayList<>()).build();

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        doReturn(courseList).when(courseRepo).getAllCourses();

        assertEquals(courseList, courseQuerryService.getAllCourses());

    }

    @Test
    public void getAllCoursesException(){

        List<Course> courseList = new ArrayList<>();

        doReturn(courseList).when(courseRepo).getAllCourses();

        assertThrows(ListEmptyException.class, () -> courseQuerryService.getAllCourses());

    }

    @Test
    public void getCourseById(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        doReturn(Optional.of(course)).when(courseRepo).getCourseById(1);

        assertEquals(course, courseQuerryService.getCourseById(1));
    }

    @Test
    public void getCourseByIdException(){
        doReturn(Optional.empty()).when(courseRepo).getCourseById(1);

        assertThrows(CourseNotFoundException.class, () -> courseQuerryService.getCourseById(1));
    }

    @Test
    public void getCourseByName(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        doReturn(Optional.of(course)).when(courseRepo).getCourseByName("Math");

        assertEquals(course, courseQuerryService.getCourseByName("Math"));
    }

    @Test
    public void getCourseByNameException(){
        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        assertThrows(CourseNotFoundException.class, () -> courseQuerryService.getCourseByName("Math"));
    }

    @Test
    public void getCourseByNameAndDepartment(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        doReturn(Optional.of(course)).when(courseRepo).getCourseByNameAndDepartment("Math", "Math");

        assertEquals(course, courseQuerryService.getCourseByNameAndDepartment("Math", "Math"));
    }

    @Test
    public void getCourseByNameAndDepartmentException(){
        doReturn(Optional.empty()).when(courseRepo).getCourseByNameAndDepartment("Math", "Math");

        assertThrows(CourseNotFoundException.class, () -> courseQuerryService.getCourseByNameAndDepartment("Math", "Math"));
    }

    @Test
    public void getCourseByDepartment(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Course course1 = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Course course2 = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Course course3 = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        doReturn(courseList).when(courseRepo).getCourseByDepartment("Math");

        assertEquals(courseList, courseQuerryService.getCourseByDepartment("Math"));
    }

    @Test
    public void getCourseByDepartmentException(){
        List<Course> courseList = new ArrayList<>();

        doReturn(courseList).when(courseRepo).getCourseByDepartment("Math");

        assertThrows(ListEmptyException.class, () -> courseQuerryService.getCourseByDepartment("Math"));
    }

    @Test
    public void addCourse(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        courseCommandService.addCourse(courseDTO);

        verify(courseRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(course, argumentCaptor.getValue());
    }

    @Test
    public void addCourseException(){
        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        doReturn(Optional.of(Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build())).when(courseRepo).getCourseByName("Math");

        assertThrows(CourseWasFoundException.class, () -> courseCommandService.addCourse(courseDTO));
    }

    @Test
    public void deleteCourse(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        doReturn(Optional.of(course)).when(courseRepo).getCourseByName("Math");

        courseCommandService.deleteCourse("Math");

        verify(courseRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(course, argumentCaptor.getValue());
    }

    @Test
    public void deleteCourseException(){
        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        assertThrows(CourseNotFoundException.class, () -> courseCommandService.deleteCourse("Math"));
    }

    @Test
    public void updateCourse(){
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();

        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        doReturn(Optional.of(course)).when(courseRepo).getCourseByName("Math");

        courseCommandService.updateCourse(courseDTO);

        verify(courseRepo,times(1)).save(argumentCaptor.capture());

        assertEquals(course, argumentCaptor.getValue());
    }

    @Test
    public void updateCourseException(){
        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        assertThrows(CourseNotFoundException.class, () -> courseCommandService.updateCourse(courseDTO));
    }

    

}