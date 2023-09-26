package ro.myclass.onlineschoolapi.studentIdCard.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardWasFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentWasFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;
import ro.myclass.onlineschoolapi.studentIdCard.dto.StudentIdCardDTO;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;
import ro.myclass.onlineschoolapi.studentIdCard.repo.StudentIdCardRepo;

import java.util.Optional;

@Service
@Transactional
public class StudentIdCardCommandImplService implements StudentIdCardCommandService {

    private StudentIdCardRepo studentIdCardRepo;

    private StudentRepo studentRepo;

    public StudentIdCardCommandImplService(StudentIdCardRepo studentIdCardRepo, StudentRepo studentRepo) {
        this.studentIdCardRepo = studentIdCardRepo;
        this.studentRepo = studentRepo;
    }
    public void addStudentIdCard(StudentIdCardDTO studentIdCardDTO) {
        Optional<StudentIdCard> studentIdCard = studentIdCardRepo.getStudentIdCardByCardNumber(studentIdCardDTO.getCardNumber());

        if(studentIdCard.isEmpty()){
            StudentDTO studentDTO = studentIdCardDTO.getStudent();

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if(student.isEmpty()){
                throw new StudentIdCardWasFoundException();
            }

            StudentIdCard studentIdCard1 = StudentIdCard.builder().cardNumber(studentIdCardDTO.getCardNumber()).student(student.get()).build();
            studentIdCardRepo.save(studentIdCard1);
        }else {
            throw new StudentWasFoundException();
        }
    }

    public void updateStudentIdCard(StudentIdCardDTO studentIdCardDTO) {
        Optional<StudentIdCard> studentIdCard = studentIdCardRepo.getStudentIdCardByCardNumber(studentIdCardDTO.getCardNumber());

        if(studentIdCard.isEmpty()){
            throw new StudentIdCardNotFoundException();
        }else {
            StudentDTO studentDTO = studentIdCardDTO.getStudent();

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if (student.isEmpty()) {
                throw new StudentNotFoundException();
            }

            StudentIdCard studentIdCard1 = StudentIdCard.builder().cardNumber(studentIdCardDTO.getCardNumber()).student(student.get()).build();
            studentIdCardRepo.save(studentIdCard1);
        }
    }

    public void deleteStudentIdCard(int cardNumber) {
        Optional<StudentIdCard> studentIdCard = studentIdCardRepo.getStudentIdCardByCardNumber(cardNumber);

        if(studentIdCard.isEmpty()){
            throw new StudentIdCardNotFoundException();
        }else{
            studentIdCardRepo.delete(studentIdCard.get());
        }
    }
}
