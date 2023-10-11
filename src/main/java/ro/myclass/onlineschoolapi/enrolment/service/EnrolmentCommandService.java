package ro.myclass.onlineschoolapi.enrolment.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.dto.RemoveEnrolmentDTO;

@Service
@Transactional
public interface EnrolmentCommandService {

    void addEnrolment(EnrolmentDTO enrolmentDTO);
    void deleteEnrolment(RemoveEnrolmentDTO removeEnrolmentDTO);
    void updateEnrolment(EnrolmentDTO enrolmentDTO);

}
