package ro.myclass.onlineschoolapi.student.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class StudentRepoTest {

    @Autowired
    public StudentRepo studentRepo;

    @BeforeEach
    public void clean() {
        studentRepo.deleteAll();
    }

    @Test
    public void getAllStudent() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu Razvan").lastName("Ionescu").age(13).email("ionescurazvan@gmail.com").adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa Cristian").lastName("Popa").age(14).email("popacristian@gmail.com").adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu Ionut").lastName("Stanciu").age(15).email("stanciuionut@gmail.com").adress("Bucuresti").build();

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        assertEquals(studentList, studentRepo.getAllStudent());

    }

    @Test
    public void getStudentById() {

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").build();

        studentRepo.save(student);

        assertEquals(student, studentRepo.getStudentById(student.getId()).get(0));

    }

    @Test
    public void getStudentByFirstName() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Popescu").lastName("Ionut").email("popescuionut@gmail.com").age(13).adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popescu").lastName("Cristian").email("popescucristian@gmail.com").age(14).adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Popescu").lastName("Razvan").email("popescurazvan@gmail.com").age(15).adress("Bucuresti").build();

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        assertEquals(studentList, studentRepo.findStudentsByFirstName("Popescu"));


    }

    @Test
    public void getStudentByLastName() {


        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Andrei").email("ionescuandrei@gmail.com").age(13).adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Andrei").email("popaandrei@gmail.com").age(14).adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Andrei").email("stanciuandrei@gmail.com").age(15).adress("Bucuresti").build();


        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        assertEquals(studentList, studentRepo.findStudentsByLastName("Andrei"));
    }

    @Test
    public void getStudentByAge() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Andrei").email("ionescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Andrei").email("popaandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Andrei").email("stanciuandrei@gmail.com").age(12).adress("Bucuresti").build();

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        assertEquals(studentList, studentRepo.findStudentsByAge(12));

    }

    @Test
    public void getStudentByEmail() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();

        studentRepo.save(student);

        assertEquals(student, studentRepo.findStudentsByEmail(student.getEmail()).get(0));
    }

    @Test
    public void getStudentByAdress() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student1 = Student.builder().firstName("Ionescu").lastName("Andrei").email("ionescuandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student2 = Student.builder().firstName("Popa").lastName("Andrei").email("popaandrei@gmail.com").age(12).adress("Bucuresti").build();
        Student student3 = Student.builder().firstName("Stanciu").lastName("Andrei").email("stanciuandrei@gmail.com").age(12).adress("Bucuresti").build();

        studentRepo.save(student);
        studentRepo.save(student1);
        studentRepo.save(student2);
        studentRepo.save(student3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        assertEquals(studentList, studentRepo.findStudentsByAdress("Bucuresti"));
    }

    @Test
    public void getStudentByFirstNameAndLastName() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();

        studentRepo.save(student);

        assertEquals(student, studentRepo.findStudentByFirstNameAndLastName("Popescu", "Andrei").get());
    }

    @Test
    public void getStudentByFirstNameAndLastNameAndAge() {

        Student student = Student.builder().firstName("Popescu").lastName("Andrei").email("popescuandrei@gmail.com").age(12).adress("Bucuresti").build();

        studentRepo.save(student);

        assertEquals(student, studentRepo.findStudentByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12).get());
    }

    
}

