package ro.myclass.onlineschoolapi.student.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentQuerryService  {

    List<Student> getAllStudents();

    Student getStudentById(int id);

    Student getStudentByFirstNameAndLastName(String firstName, String lastName);

    Student getStudentByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    List<Student> getStudentsByFirstName(String firstName);

    List<Student> getStudentsByLastName(String lastName);

    List<Student> getStudentsByAge(int age);

    Student getStudentByEmail(String email);

    List<Student> getStudentsByAdress(String adress);





}
