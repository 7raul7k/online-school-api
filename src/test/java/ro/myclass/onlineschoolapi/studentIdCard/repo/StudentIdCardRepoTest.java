package ro.myclass.onlineschoolapi.studentIdCard.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class StudentIdCardRepoTest {

    @Autowired
    public StudentIdCardRepo studentIdCardRepo;

    @Autowired
    public StudentRepo studentRepo;

    @BeforeEach
    public void clean() {
        studentIdCardRepo.deleteAll();
    }

    @Test
    public void getAllStudentIdCard() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Razvan").age(13).email("").adress("").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Cristian").age(14).email("").adress("").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Ionut").age(15).email("").adress("").build();

        StudentIdCard studentIdCard = StudentIdCard.builder().student(student).cardNumber(123).build();
        StudentIdCard studentIdCard1 = StudentIdCard.builder().student(student1).cardNumber(1234).build();
        StudentIdCard studentIdCard2 = StudentIdCard.builder().student(student2).cardNumber(12345).build();
        StudentIdCard studentIdCard3 = StudentIdCard.builder().student(student3).cardNumber(123456).build();

        studentIdCardRepo.save(studentIdCard);
        studentIdCardRepo.save(studentIdCard1);
        studentIdCardRepo.save(studentIdCard2);
        studentIdCardRepo.save(studentIdCard3);

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);


        List<StudentIdCard> studentIdCardList = new ArrayList<>();
        studentIdCardList.add(studentIdCard);
        studentIdCardList.add(studentIdCard1);
        studentIdCardList.add(studentIdCard2);
        studentIdCardList.add(studentIdCard3);

        assertEquals(studentIdCardList, studentIdCardRepo.getAllStudentIdCard());
    }

   @Test
    public void getStudentIdCardByIdTest() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        StudentIdCard studentIdCard = StudentIdCard.builder().student(student).cardNumber(123).build();

        studentIdCardRepo.save(studentIdCard);

        assertEquals(studentIdCard, studentIdCardRepo.getStudentIdCardById(studentIdCard.getId()).get());
    }

    @Test
    public void getStudentIdCardById() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        StudentIdCard studentIdCard = StudentIdCard.builder().student(student).cardNumber(123).build();

        studentIdCardRepo.save(studentIdCard);
    }

    @Test
    public void getStudentIdCardByStudentId() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        StudentIdCard studentIdCard = StudentIdCard.builder().student(student).cardNumber(123).build();

        studentIdCardRepo.save(studentIdCard);

    }

    @Test
    public void getStudentIdCardByStudent(){

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("").build();

        StudentIdCard studentIdCard = StudentIdCard.builder().student(student).cardNumber(123).build();

        studentIdCardRepo.save(studentIdCard);

        assertEquals(studentIdCard,studentIdCardRepo.getStudentIdCardByStudentId(student.getId()).get());
    }

    @Test
    public void getStudentIdCardByCardNumberTest(){

        StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber(123).build();

        studentIdCardRepo.save(studentIdCard);

        assertEquals(studentIdCard,studentIdCardRepo.getStudentIdCardByCardNumber("123").get());
    }
}