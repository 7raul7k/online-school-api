package ro.myclass.onlineschoolapi.enrolment.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.exceptions.EnrolmentNotFoundException;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class EnrolmentRepoTest {

    @Autowired
    public EnrolmentRepo enrolmentRepo;

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public CourseRepo courseRepo;

    @BeforeEach
    public void clean() {
        enrolmentRepo.deleteAll();
    }


    @Test
    public void getAllEnrolments() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();
        Student student1 = Student.builder().firstName("Ionescu Razvan").lastName("Ionescu").age(13).email("").adress("").build();
        Student student2 = Student.builder().firstName("Popa Cristian").lastName("Popa").age(14).email("").adress("").build();
        Student student3 = Student.builder().firstName("Stanciu Ionut").lastName("Stanciu").age(15).email("").adress("").build();

        Course course = Course.builder().name("Matematica").department("Matematica").build();
        Course course1 = Course.builder().name("Romana").department("Romana").build();
        Course course2 = Course.builder().name("Istorie").department("Istorie").build();
        Course course3 = Course.builder().name("Geografie").department("Geografie").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment1 = Enrolment.builder().student(student1).course(course1).build();
        Enrolment enrolment2 = Enrolment.builder().student(student2).course(course2).build();
        Enrolment enrolment3 = Enrolment.builder().student(student3).course(course3).build();
        Enrolment enrolment4 = Enrolment.builder().student(student).course(course1).build();
        Enrolment enrolment5 = Enrolment.builder().student(student1).course(course2).build();
        Enrolment enrolment6 = Enrolment.builder().student(student2).course(course3).build();
        Enrolment enrolment7 = Enrolment.builder().student(student3).course(course).build();

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        courseRepo.save(course);
        courseRepo.save(course1);
        courseRepo.save(course2);
        courseRepo.save(course3);


        enrolmentRepo.save(enrolment);
        enrolmentRepo.save(enrolment1);
        enrolmentRepo.save(enrolment2);
        enrolmentRepo.save(enrolment3);
        enrolmentRepo.save(enrolment4);
        enrolmentRepo.save(enrolment5);
        enrolmentRepo.save(enrolment6);
        enrolmentRepo.save(enrolment7);

        List<Enrolment> enrolmentList = List.of(enrolment, enrolment1, enrolment2, enrolment3, enrolment4, enrolment5, enrolment6, enrolment7);

        assertEquals(enrolmentList, enrolmentRepo.getAllEnrolments());
    }

    @Test
    public void getEnrolmentByStudentId() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();
        Student student1 = Student.builder().firstName("Ionescu Razvan").lastName("Ionescu").age(13).email("").adress("").build();
        Student student2 = Student.builder().firstName("Popa Cristian").lastName("Popa").age(14).email("").adress("").build();
        Student student3 = Student.builder().firstName("Stanciu Ionut").lastName("Stanciu").age(15).email("").adress("").build();

        Course course = Course.builder().name("Matematica").department("Matematica").build();
        Course course1 = Course.builder().name("Romana").department("Romana").build();
        Course course2 = Course.builder().name("Istorie").department("Istorie").build();
        Course course3 = Course.builder().name("Geografie").department("Geografie").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment2 = Enrolment.builder().student(student).course(course1).build();
        Enrolment enrolment3 = Enrolment.builder().student(student).course(course2).build();


        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        courseRepo.save(course);
        courseRepo.save(course1);
        courseRepo.save(course2);
        courseRepo.save(course3);

        enrolmentRepo.save(enrolment);
        enrolmentRepo.save(enrolment2);
        enrolmentRepo.save(enrolment3);

        List<Enrolment> enrolmentList = List.of(enrolment, enrolment2, enrolment3);

        assertEquals(enrolmentList, enrolmentRepo.getEnrolmentsByStudentId(student.getId()));
    }

    @Test
    public void getEnrolmentByCourseId() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();
        Student student1 = Student.builder().firstName("Ionescu Razvan").lastName("Ionescu").age(13).email("").adress("").build();
        Student student2 = Student.builder().firstName("Popa Cristian").lastName("Popa").age(14).email("").adress("").build();
        Student student3 = Student.builder().firstName("Stanciu Ionut").lastName("Stanciu").age(15).email("").adress("").build();

        Course course = Course.builder().name("Matematica").department("Matematica").build();
        Course course1 = Course.builder().name("Romana").department("Romana").build();
        Course course2 = Course.builder().name("Istorie").department("Istorie").build();
        Course course3 = Course.builder().name("Geografie").department("Geografie").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();
        Enrolment enrolment2 = Enrolment.builder().student(student1).course(course).build();
        Enrolment enrolment3 = Enrolment.builder().student(student2).course(course).build();


        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        courseRepo.save(course);
        courseRepo.save(course1);
        courseRepo.save(course2);
        courseRepo.save(course3);

        enrolmentRepo.save(enrolment);
        enrolmentRepo.save(enrolment2);
        enrolmentRepo.save(enrolment3);

        List<Enrolment> enrolmentList = List.of(enrolment, enrolment2, enrolment3);

        assertEquals(enrolmentList, enrolmentRepo.getEnrolmentsByCourseId(course.getId()));
    }

    @Test
    public void getEnrolmentById() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();

        Course course = Course.builder().name("Matematica").department("Matematica").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        studentRepo.save(student);

        courseRepo.save(course);

        enrolmentRepo.save(enrolment);

        assertEquals(enrolment, enrolmentRepo.getEnrolmentById(enrolment.getId()).get());

    }

    @Test
    public void getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();

        Course course = Course.builder().name("Matematica").department("Matematica").build();

        Enrolment enrolment = Enrolment.builder().student(student).course(course).build();

        studentRepo.save(student);

        courseRepo.save(course);

        enrolmentRepo.save(enrolment);

        assertEquals(enrolment, enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(course.getName(), student.getFirstName(), student.getLastName()).get());

    }
}