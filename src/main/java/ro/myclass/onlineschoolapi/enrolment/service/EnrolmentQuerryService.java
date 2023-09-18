package ro.myclass.onlineschoolapi.enrolment.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;

import java.util.List;

@Service
public interface EnrolmentQuerryService {

    List<Enrolment> getAllEnrolments();
    Enrolment getEnrolmentById(long id);
    List<Enrolment> getEnrolmentByStudentId(long studentId);
    List<Enrolment> getEnrolmentByCourseId(long courseId);

}
