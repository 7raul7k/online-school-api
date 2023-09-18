package ro.myclass.onlineschoolapi.studentIdCard.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.studentIdCard.dto.StudentIdCardDTO;

@Service
@Transactional
public interface StudentIdCardCommandService {

    void addStudentIdCard(StudentIdCardDTO studentIdCardDTO);

    void updateStudentIdCard(StudentIdCardDTO studentIdCardDTO);

    void deleteStudentIdCard(int cardNumber);

}
