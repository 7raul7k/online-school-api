package ro.myclass.onlineschoolapi.enrolment.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;

@Service
@Transactional
public interface EnrolmentCommandService {

    void addEnrolment(EnrolmentDTO enrolmentDTO);
    void deleteEnrolment(long id);
    void updateEnrolment(EnrolmentDTO enrolmentDTO);

}
