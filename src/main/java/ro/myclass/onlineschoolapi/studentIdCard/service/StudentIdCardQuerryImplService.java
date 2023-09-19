package ro.myclass.onlineschoolapi.studentIdCard.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentIdCardWasFoundException;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;
import ro.myclass.onlineschoolapi.studentIdCard.repo.StudentIdCardRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentIdCardQuerryImplService implements StudentIdCardQuerryService{

    private StudentIdCardRepo studentIdCardRepo;

    public StudentIdCardQuerryImplService(StudentIdCardRepo studentIdCardRepo) {
        this.studentIdCardRepo = studentIdCardRepo;
    }

    public List<StudentIdCard> getAllStudentIdCards() {
        List<StudentIdCard> studentIdCardList = studentIdCardRepo.getAllStudentIdCard();

        if (studentIdCardList.isEmpty()) {
            throw new ListEmptyException();
        }

        return studentIdCardList;
    }

    public StudentIdCard getStudentIdCardById(long id) {
        Optional<StudentIdCard> studentIdCard = studentIdCardRepo.getStudentIdCardById(id);

        if (studentIdCard.isEmpty()) {
            throw new StudentIdCardNotFoundException();
        }

        return studentIdCard.get();
    }

    public StudentIdCard getStudentIdCardByStudent(long studentId) {
        Optional<StudentIdCard> studentIdCard = studentIdCardRepo.getStudentIdCardByStudentId(studentId);

        if (studentIdCard.isEmpty()) {
            throw new StudentIdCardNotFoundException();
        }

        return studentIdCard.get();
    }


}
