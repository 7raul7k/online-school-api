package ro.myclass.onlineschoolapi.student.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;

@Service
@Transactional
public interface StudentCommandService {

    void addStudent(StudentDTO studentDTO);
    void deleteStudent(String firstName, String lastName);

    void updateStudent(StudentDTO studentDTO);
}
