package ro.myclass.onlineschoolapi.studentIdCard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardWasFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;
import ro.myclass.onlineschoolapi.studentIdCard.dto.StudentIdCardDTO;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;
import ro.myclass.onlineschoolapi.studentIdCard.repo.StudentIdCardRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StudentIdCardServiceTest {

    @Mock
    private StudentIdCardRepo studentIdCardRepo;

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentIdCardCommandService studentIdCardCommandService = new StudentIdCardCommandImplService(studentIdCardRepo, studentRepo);

    @InjectMocks
    private StudentIdCardQuerryService studentIdCardQuerryService = new StudentIdCardQuerryImplService(studentIdCardRepo);

    @Captor
    private ArgumentCaptor<StudentIdCard> argumentCaptor;

    @Test
    public void getAllStudentIdCards() {

        Student student = Student.builder().email("").firstName("Popescu").lastName("Ion").adress("Bucuresti").email("").age(12).build();
        Student student1 = Student.builder().email("").firstName("Andrei").lastName("Ionut").adress("Bucuresti").email("").age(12).build();
        Student student2 = Student.builder().email("").firstName("Mihai").lastName("Popescu").adress("Bucuresti").email("").age(12).build();
        Student student3 = Student.builder().email("").firstName("Ion").lastName("Mihai").adress("Bucuresti").email("").age(12).build();

        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(student).build();
        StudentIdCard studentIdCard1 = StudentIdCard.builder().cardNumber(1234).student(student1).build();
        StudentIdCard studentIdCard2 = StudentIdCard.builder().cardNumber(12345).student(student2).build();
        StudentIdCard studentIdCard3 = StudentIdCard.builder().cardNumber(123456).student(student3).build();

        List<StudentIdCard> studentIdCardList = new ArrayList<>();
        studentIdCardList.add(studentIdCard);
        studentIdCardList.add(studentIdCard1);
        studentIdCardList.add(studentIdCard2);
        studentIdCardList.add(studentIdCard3);

        doReturn(studentIdCardList).when(studentIdCardRepo).getAllStudentIdCard();

        assertEquals(studentIdCardList, studentIdCardQuerryService.getAllStudentIdCards());

    }

    @Test
    public void getAllStudentIdCardsException(){
        List<StudentIdCard> studentIdCardList = new ArrayList<>();

        doReturn(studentIdCardList).when(studentIdCardRepo).getAllStudentIdCard();

        assertThrows(ListEmptyException.class, () -> studentIdCardQuerryService.getAllStudentIdCards());
    }

    @Test
    public void getStudentIdCardById() {
        Student student = Student.builder().email("").firstName("Popescu").lastName("Ion").adress("Bucuresti").email("").age(12).build();
        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(student).build();

        doReturn(Optional.of(studentIdCard)).when(studentIdCardRepo).getStudentIdCardById(123);

        assertEquals(studentIdCard, studentIdCardQuerryService.getStudentIdCardById(123));
    }

    @Test
    public void getStudentIdCardByIdException() {

        doReturn(Optional.empty()).when(studentIdCardRepo).getStudentIdCardById(123);

        assertThrows(StudentIdCardNotFoundException.class, () -> studentIdCardQuerryService.getStudentIdCardById(123));
    }

    @Test
    public void getStudentIdCardByStudent() {
        Student student = Student.builder().email("").firstName("Popescu").lastName("Ion").adress("Bucuresti").email("").age(12).build();
        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(student).build();

        doReturn(Optional.of(studentIdCard)).when(studentIdCardRepo).getStudentIdCardByStudentId(123);

        assertEquals(studentIdCard, studentIdCardQuerryService.getStudentIdCardByStudent(123));
    }

    @Test
    public void getStudentIdCardByStudentException() {

        doReturn(Optional.empty()).when(studentIdCardRepo).getStudentIdCardByStudentId(123);

        assertThrows(StudentIdCardNotFoundException.class, () -> studentIdCardQuerryService.getStudentIdCardByStudent(123));
    }

    @Test
    public void addStudentIdCard() {
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(student).build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();
        doReturn(Optional.empty()).when(studentIdCardRepo).getStudentIdCardByCardNumber(123);
        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("email");
        doReturn(studentIdCard).when(studentIdCardRepo).save(argumentCaptor.capture());

        studentIdCardCommandService.addStudentIdCard(studentIdCardDTO);

        assertEquals(studentIdCard, argumentCaptor.getValue());
    }

    @Test
    public void addStudentIdCardStudentException() {
        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("email");

        assertThrows(StudentNotFoundException.class, () -> studentIdCardCommandService.addStudentIdCard(studentIdCardDTO));
    }


    @Test
    public void addStudentIdCardException(){
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        doReturn(Optional.empty()).when(studentIdCardRepo).getStudentIdCardByCardNumber(123);

        assertThrows(StudentIdCardWasFoundException.class, () -> studentIdCardCommandService.addStudentIdCard(studentIdCardDTO));
    }

    @Test
    public void updateStudentIdCard() {
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(student).build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        doReturn(Optional.of(studentIdCard)).when(studentIdCardRepo).getStudentIdCardByCardNumber(123);
        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("email");
        doReturn(studentIdCard).when(studentIdCardRepo).save(argumentCaptor.capture());

        studentIdCardCommandService.updateStudentIdCard(studentIdCardDTO);

        assertEquals(studentIdCard, argumentCaptor.getValue());
    }

    @Test
    public void updateStudentIdCardStudentException() {
        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).student(Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build()).build();
        StudentIdCardDTO studentIdCardDTO1 = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        doReturn(Optional.of(studentIdCard)).when(studentIdCardRepo).getStudentIdCardByCardNumber(123);
        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("email");


        assertThrows(StudentNotFoundException.class, () -> studentIdCardCommandService.updateStudentIdCard(studentIdCardDTO));
    }

    @Test
    public void updateStudentIdCardException() {
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        StudentIdCardDTO studentIdCardDTO = StudentIdCardDTO.builder().cardNumber(123).student(studentDTO).build();

        doReturn(Optional.empty()).when(studentIdCardRepo).getStudentIdCardByCardNumber(123);

        assertThrows(StudentIdCardNotFoundException.class, () -> studentIdCardCommandService.updateStudentIdCard(studentIdCardDTO));
    }
}