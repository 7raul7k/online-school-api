package ro.myclass.onlineschoolapi.enrolment.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.repo.EnrolmentRepo;
import ro.myclass.onlineschoolapi.exceptions.EnrolmentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolmentQuerryImplService implements EnrolmentQuerryService {

    private EnrolmentRepo enrolmentRepo;

    public EnrolmentQuerryImplService(EnrolmentRepo enrolmentRepo) {
        this.enrolmentRepo = enrolmentRepo;
    }

    public List<Enrolment> getAllEnrolments() {
        List<Enrolment> enrolmentList = enrolmentRepo.getAllEnrolments();

        if (enrolmentList.isEmpty()) {
            throw new ListEmptyException();
        }

        return enrolmentList;
    }

    public Enrolment getEnrolmentById(long id) {
       Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentById(id);

        if (enrolment.isEmpty()) {
            throw new EnrolmentNotFoundException();
        }

        return enrolment.get();
    }

      public List<Enrolment> getEnrolmentByStudentId(long studentId) {
            List<Enrolment> enrolment = enrolmentRepo.getEnrolmentsByStudentId(studentId);

            if (enrolment.isEmpty()) {
                throw new EnrolmentNotFoundException();
            }

            return enrolment;
        }

        public List<Enrolment> getEnrolmentByCourseId(long courseId) {
            List<Enrolment> enrolment = enrolmentRepo.getEnrolmentsByCourseId(courseId);

            if (enrolment.isEmpty()) {
                throw new EnrolmentNotFoundException();
            }

            return enrolment;
        }




}
