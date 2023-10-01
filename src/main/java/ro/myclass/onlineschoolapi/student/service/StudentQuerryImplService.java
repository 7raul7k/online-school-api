package ro.myclass.onlineschoolapi.student.service;


import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentWasFoundException;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQuerryImplService implements StudentQuerryService {

    private StudentRepo studentRepo;

    public StudentQuerryImplService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepo.getAllStudent();

        if (studentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentList;
    }

    public Student getStudentById(int id) {
        Optional<Student> student = studentRepo.getStudentById(id);

        if (student.isEmpty()) {
            throw new StudentNotFoundException();
        }

        return student.get();
    }

    public Student getStudentByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Student> student = studentRepo.findStudentByFirstNameAndLastName(firstName, lastName);

        if (student.isEmpty()) {
            throw new StudentNotFoundException();
        }

        return student.get();
    }

    public Student getStudentByFirstNameAndLastNameAndAge(String firstName, String lastName, int age) {
        Optional<Student> student = studentRepo.findStudentByFirstNameAndLastNameAndAge(firstName, lastName, age);

        if (student.isEmpty()) {
            throw new StudentNotFoundException();
        }

        return student.get();
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        List<Student> studentList = studentRepo.findStudentsByFirstName(firstName);

        if (studentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentList;
    }

    public List<Student> getStudentsByLastName(String lastName) {
        List<Student> studentList = studentRepo.findStudentsByLastName(lastName);

        if (studentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentList;
    }

    public List<Student> getStudentsByAge(int age) {
        List<Student> studentList = studentRepo.findStudentsByAge(age);

        if (studentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentList;
    }


    public List<Student> getStudentsByAdress(String adress) {
        List<Student> studentList = studentRepo.findStudentsByAdress(adress);

        if (studentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentList;
    }

    public Student getStudentByEmail(String email) {
        Optional<Student> student = studentRepo.findStudentByEmail(email);

        if (student.isEmpty()) {
            throw new StudentNotFoundException();
        }

        return student.get();
    }



}
