package ro.myclass.onlineschoolapi.enrolment.service;

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
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.repo.EnrolmentRepo;
import ro.myclass.onlineschoolapi.exceptions.*;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrolmentServiceTest {

    @Mock
    private EnrolmentRepo enrolmentRepo;

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private EnrolmentCommandService enrolmentCommandService = new EnrolmentCommandImplService(enrolmentRepo, studentRepo, courseRepo);

    @InjectMocks
    private EnrolmentQuerryService enrolmentQuerryService = new EnrolmentQuerryImplService(enrolmentRepo);

    @Captor
    private ArgumentCaptor<Enrolment> argumentCaptor;

    @Test
    public void getAllEnrolments() {
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Course course1 = Course.builder().name("English").department("English").enrolment(new ArrayList<>()).build();
        Course course2 = Course.builder().name("French").department("French").enrolment(new ArrayList<>()).build();
        Course course3 = Course.builder().name("German").department("German").enrolment(new ArrayList<>()).build();

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Razvan").age(13).email("").adress("").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Cristian").age(14).email("").adress("").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Ionut").age(15).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment1 = Enrolment.builder().student(student1).course(course1).build();
        Enrolment enrolment2 = Enrolment.builder().student(student2).course(course2).build();
        Enrolment enrolment3 = Enrolment.builder().student(student3).course(course3).build();

        List<Enrolment> enrolmentList = new ArrayList<>();
        enrolmentList.add(enrolment);
        enrolmentList.add(enrolment1);
        enrolmentList.add(enrolment2);
        enrolmentList.add(enrolment3);

        doReturn(enrolmentList).when(enrolmentRepo).getAllEnrolments();

        assertEquals(enrolmentList, enrolmentQuerryService.getAllEnrolments());
    }

    @Test
    public void getAllEnrolmentsException() {

        List<Enrolment> enrolmentList = new ArrayList<>();

        doReturn(enrolmentList).when(enrolmentRepo).getAllEnrolments();

        assertThrows(ListEmptyException.class, () -> enrolmentQuerryService.getAllEnrolments());

    }

    @Test
    public void getEnrolmentById() {
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentById(1);

        assertEquals(enrolment, enrolmentQuerryService.getEnrolmentById(1));
    }

    @Test
    public void getEnrolmentByIdException() {
        doReturn(Optional.empty()).when(enrolmentRepo).getEnrolmentById(1);

        assertThrows(EnrolmentNotFoundException.class, () -> enrolmentQuerryService.getEnrolmentById(1));
    }

    @Test
    public void getEnrolmentByStudentId() {
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment1 = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment2 = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment3 = Enrolment.builder().student(student).course(course).build();

        List<Enrolment> enrolmentList = new ArrayList<>();
        enrolmentList.add(enrolment);
        enrolmentList.add(enrolment1);
        enrolmentList.add(enrolment2);
        enrolmentList.add(enrolment3);

        doReturn(enrolmentList).when(enrolmentRepo).getEnrolmentsByStudentId(1);

        assertEquals(enrolmentList, enrolmentQuerryService.getEnrolmentByStudentId(1));
    }

    @Test
    public void getEnrolmentByStudentIdException() {


        doReturn(new ArrayList<>()).when(enrolmentRepo).getEnrolmentsByStudentId(1);

        assertThrows(ListEmptyException.class, () -> enrolmentQuerryService.getEnrolmentByStudentId(1));
    }

    @Test
    public void getEnrolmentByCourseId() {
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment1 = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment2 = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment3 = Enrolment.builder().student(student).course(course).build();

        List<Enrolment> enrolmentList = new ArrayList<>();
        enrolmentList.add(enrolment);
        enrolmentList.add(enrolment1);
        enrolmentList.add(enrolment2);
        enrolmentList.add(enrolment3);

        doReturn(enrolmentList).when(enrolmentRepo).getEnrolmentsByCourseId(1);

        assertEquals(enrolmentList, enrolmentQuerryService.getEnrolmentByCourseId(1));
    }

    @Test
    public void getEnrolmentByCourseIdException() {
        doReturn(new ArrayList<>()).when(enrolmentRepo).getEnrolmentsByCourseId(1);

        assertThrows(EnrolmentNotFoundException.class, () -> enrolmentQuerryService.getEnrolmentByCourseId(1));
    }

    @Test
    public void addEnrolment() {

        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        EnrolmentDTO enrolmentDTO = EnrolmentDTO.builder().studentEmail("").studentFirstName("Popescu").studentLastName("Andrei").courseName("Math").build();
        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.empty()).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");
        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("");
        doReturn(Optional.of(course)).when(courseRepo).getCourseByName("Math");
        enrolmentCommandService.addEnrolment(enrolmentDTO);

        verify(enrolmentRepo, times(1)).save(argumentCaptor.capture());

        assertEquals(enrolment, argumentCaptor.getValue());

    }


    @Test
    public void addEnrolmentStudentException() {

        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("");

        EnrolmentDTO enrolmentDTO = EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build();

        assertThrows(StudentNotFoundException.class, () -> enrolmentCommandService.addEnrolment(enrolmentDTO));
    }

    @Test
    public void addEnrolmentCourseException() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();
        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("");
        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        EnrolmentDTO enrolmentDTO = EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build();

        assertThrows(CourseNotFoundException.class, () -> enrolmentCommandService.addEnrolment(enrolmentDTO));
    }

    @Test
    public void addEnrolmentException() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        CourseDTO courseDTO = CourseDTO.builder().name("Math").department("Math").build();
        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        EnrolmentDTO enrolmentDTO = EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");


        assertThrows(EnrolmentWasFoundException.class, () -> enrolmentCommandService.addEnrolment(enrolmentDTO));

    }

    @Test
    public void deleteEnrolment() {

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentById(1);

        enrolmentCommandService.deleteEnrolment(1);

        verify(enrolmentRepo, times(1)).delete(argumentCaptor.capture());

        assertEquals(enrolment, argumentCaptor.getValue());
    }

    @Test
    public void deleteEnrolmentException() {

        doReturn(Optional.empty()).when(enrolmentRepo).getEnrolmentById(1);

        assertThrows(EnrolmentNotFoundException.class, () -> enrolmentCommandService.deleteEnrolment(1));
    }

    @Test
    public void updateEnrolment() {

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(course.getName(), student.getFirstName(), student.getLastName());

        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail(student.getEmail());

        doReturn(Optional.of(course)).when(courseRepo).getCourseByName(course.getName());

        enrolmentCommandService.updateEnrolment(EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build());

        verify(enrolmentRepo, times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(enrolment, argumentCaptor.getValue());
    }

    @Test
    public void updateEnrolmentStudentException() {

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");

        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("");


        assertThrows(StudentNotFoundException.class, () -> enrolmentCommandService.updateEnrolment(EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build()));
    }

    @Test
    public void updateEnrolmentCourseException() {

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");

        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("");

        doReturn(Optional.empty()).when(courseRepo).getCourseByName("Math");

        assertThrows(CourseNotFoundException.class, () -> enrolmentCommandService.updateEnrolment(EnrolmentDTO.builder().studentEmail("").studentFirstName("Popescu").studentLastName("Andrei").courseName("Math").build()));
    }
        @Test
    public void updateEnrolmentException() {

        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.empty()).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");

        EnrolmentDTO.builder().studentFirstName("Popescu").studentLastName("Andrei").studentEmail("").courseName("Math").build();
        assertThrows(EnrolmentNotFoundException.class, () -> enrolmentCommandService.updateEnrolment(EnrolmentDTO.builder().studentEmail("").studentFirstName("Popescu").studentLastName("Andrei").courseName("Math").build()));
        }

    @Test
    public void getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName() {
        Course course = Course.builder().name("Math").department("Math").enrolment(new ArrayList<>()).build();
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        doReturn(Optional.of(enrolment)).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");

        assertEquals(enrolment, enrolmentQuerryService.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei"));

    }

    @Test
    public void getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastNameException() {
        doReturn(Optional.empty()).when(enrolmentRepo).getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei");

        assertThrows(EnrolmentNotFoundException.class, () -> enrolmentQuerryService.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName("Math", "Popescu", "Andrei"));
    }
}
