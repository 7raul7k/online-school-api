package ro.myclass.onlineschoolapi.student.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentWasFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.Optional;

@Service
@Transactional
public class StudentCommandImplService implements StudentCommandService {

    private StudentRepo studentRepo;

    public StudentCommandImplService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void addStudent(StudentDTO studentDTO) {

        Optional<Student> student = studentRepo.findStudentByFirstNameAndLastName(studentDTO.getFirstName(), studentDTO.getLastName());

        if(student.isEmpty()){
            Student student1 = Student.builder().firstName(studentDTO.getFirstName()).lastName(studentDTO.getLastName()).age(studentDTO.getAge()).email(studentDTO.getEmail()).adress(studentDTO.getAdress()).build();

            studentRepo.save(student1);
        }

        throw new StudentWasFoundException();

    }

    public void deleteStudent(String firstName, String lastName) {

        Optional<Student> student = studentRepo.findStudentByFirstNameAndLastName(firstName, lastName);

      if(student.isEmpty()){
            throw new StudentNotFoundException();
      }else{
            studentRepo.delete(student.get());
      }

    }

    public void updateStudent(StudentDTO studentDTO){

        Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());
        if(student.isEmpty()) {
            throw new StudentNotFoundException();
        }else {

            if (studentDTO.getFirstName() != null) {
                student.get().setFirstName(studentDTO.getFirstName());
            }
            if (studentDTO.getLastName() != null) {
                student.get().setLastName(studentDTO.getLastName());
            }if (studentDTO.getAge() != 0) {
                student.get().setAge(studentDTO.getAge());
            }if(studentDTO.getEmail() != null){
                student.get().setEmail(studentDTO.getEmail());
            }

            this.studentRepo.saveAndFlush(student.get());
            }
     }




}
