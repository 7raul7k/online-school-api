package ro.myclass.onlineschoolapi.student.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentWasFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentCommandService studentCommandService = new StudentCommandImplService(studentRepo);

    @InjectMocks
    private StudentQuerryService studentQuerryService = new StudentQuerryImplService(studentRepo);
    @Captor
    private ArgumentCaptor<Student> argumentCaptor;

    @Test
    public void getAllStudents() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Razvan").age(13).email("").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Cristian").age(14).email("").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Ionut").age(15).email("").adress("Bucuresti").build();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        doReturn(studentList).when(studentRepo).getAllStudent();

        assertEquals(studentList, studentQuerryService.getAllStudents());
    }

    @Test
    public void getAllStudentException() {
        doReturn(new ArrayList<>()).when(studentRepo).getAllStudent();

        assertThrows(ListEmptyException.class, () -> studentQuerryService.getAllStudents());

    }

    @Test
    public void getStudentById() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        doReturn(java.util.Optional.of(student)).when(studentRepo).getStudentById(1);

        assertEquals(student, studentQuerryService.getStudentById(1));
    }

    @Test
    public void getStudentByIdException() {
        doReturn(java.util.Optional.empty()).when(studentRepo).getStudentById(1);

        assertThrows(StudentNotFoundException.class, () -> studentQuerryService.getStudentById(1));
    }

    @Test
    public void getStudentByFirstNameAndLastName() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        doReturn(java.util.Optional.of(student)).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");

        assertEquals(student, studentQuerryService.getStudentByFirstNameAndLastName("Popescu", "Andrei"));
    }

    @Test
    public void getStudentByFirstNameAndLastNameException() {
        doReturn(java.util.Optional.empty()).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");

        assertThrows(StudentNotFoundException.class, () -> studentQuerryService.getStudentByFirstNameAndLastName("Popescu", "Andrei"));
    }

    @Test
    public void getStudentByFirstNameAndLastNameAndAge() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        doReturn(java.util.Optional.of(student)).when(studentRepo).findStudentByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12);

        assertEquals(student, studentQuerryService.getStudentByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12));
    }

    @Test
    public void getStudentByFirstNameAndLastNameAndAgeException() {
        doReturn(java.util.Optional.empty()).when(studentRepo).findStudentByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12);

        assertThrows(StudentNotFoundException.class, () -> studentQuerryService.getStudentByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12));
    }

    @Test
    public void getStudentsByFirstName() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Popescu").lastName("Razvan").age(13).email("").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popescu").lastName("Cristian").age(14).email("").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Popescu").lastName("Ionut").age(15).email("").adress("Bucuresti").build();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        doReturn(studentList).when(studentRepo).findStudentsByFirstName("Popescu");

        assertEquals(studentList, studentQuerryService.getStudentsByFirstName("Popescu"));
    }

    @Test
    public void getStudentsByFirstNameException() {
        doReturn(new ArrayList<>()).when(studentRepo).findStudentsByFirstName("Popescu");

        assertThrows(ListEmptyException.class, () -> studentQuerryService.getStudentsByFirstName("Popescu"));
    }

    @Test
    public void getStudentsByLastName() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Andrei").age(13).email("").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Andrei").age(14).email("").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Andrei").age(15).email("").adress("Bucuresti").build();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        doReturn(studentList).when(studentRepo).findStudentsByLastName("Andrei");

        assertEquals(studentList, studentQuerryService.getStudentsByLastName("Andrei"));
    }

    @Test
    public void getStudentsByLastNameException() {
        doReturn(new ArrayList<>()).when(studentRepo).findStudentsByLastName("Andrei");

        assertThrows(ListEmptyException.class, () -> studentQuerryService.getStudentsByLastName("Andrei"));
    }

    @Test
    public void getStudentsByAge() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Razvan").age(12).email("").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Cristian").age(12).email("").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Ionut").age(12).email("").adress("Bucuresti").build();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        doReturn(studentList).when(studentRepo).findStudentsByAge(12);

        assertEquals(studentList, studentQuerryService.getStudentsByAge(12));
    }

    @Test
    public void getStudentsByAgeException() {
        doReturn(new ArrayList<>()).when(studentRepo).findStudentsByAge(12);

        assertThrows(ListEmptyException.class, () -> studentQuerryService.getStudentsByAge(12));
    }

    @Test
    public void getStudentByEmail() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("popescuandrei@gmail.com");

        assertEquals(student, studentQuerryService.getStudentByEmail("popescuandrei@gmail.com"));


    }

    @Test
    public void getStudentByEmailException() {
        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("");

        assertThrows(StudentNotFoundException.class, () -> studentQuerryService.getStudentByEmail(""));
    }

    @Test
    public void getStudentsByAdress() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Razvan").age(13).email("").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Cristian").age(14).email("").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Ionut").age(15).email("").adress("Bucuresti").build();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        doReturn(studentList).when(studentRepo).findStudentsByAdress("Bucuresti");

        assertEquals(studentList, studentQuerryService.getStudentsByAdress("Bucuresti"));
    }

    @Test
    public void getStudentsByAdressException() {
        doReturn(new ArrayList<>()).when(studentRepo).findStudentsByAdress("Bucuresti");

        assertThrows(ListEmptyException.class, () -> studentQuerryService.getStudentsByAdress("Bucuresti"));
    }

    @Test
    public void addStudent() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.empty()).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");


        studentCommandService.addStudent(studentDTO);

        verify(studentRepo, times(1)).save(argumentCaptor.capture());

        assertEquals(student, argumentCaptor.getValue());
    }

    @Test
    public void addStudentException() {

        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.of(Student.builder().build())).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");

        assertThrows(StudentWasFoundException.class, () -> studentCommandService.addStudent(studentDTO));
    }

    @Test
    public void deleteStudent() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.of(student)).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");

        studentCommandService.deleteStudent("Popescu", "Andrei");

        verify(studentRepo, times(1)).delete(argumentCaptor.capture());

        assertEquals(student, argumentCaptor.getValue());
    }

    @Test
    public void deleteStudentException() {

        doReturn(Optional.empty()).when(studentRepo).findStudentByFirstNameAndLastName("Popescu", "Andrei");

        assertThrows(StudentNotFoundException.class, () -> studentCommandService.deleteStudent("Popescu", "Andrei"));
    }

    @Test
    public void updateStudent() {
        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("popescuandrei@gmail.com");

        studentCommandService.updateStudent(studentDTO);

        verify(studentRepo, times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(student, argumentCaptor.getValue());
    }

    @Test
    public void updateStudentException() {

        StudentDTO studentDTO = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("popescuandrei@gmail.com");

        assertThrows(StudentNotFoundException.class, () -> studentCommandService.updateStudent(studentDTO));
    }
}