package ro.myclass.onlineschoolapi.studentIdCard.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;

import java.util.List;

@Service
public interface StudentIdCardQuerryService {

    List<StudentIdCard> getAllStudentIdCards();

    StudentIdCard getStudentIdCardById(long id);

    StudentIdCard getStudentIdCardByStudent(long studentId);
}
