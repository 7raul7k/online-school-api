package ro.myclass.onlineschoolapi.enrolment.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.repo.EnrolmentRepo;
import ro.myclass.onlineschoolapi.exceptions.CourseNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.EnrolmentNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.EnrolmentWasFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.Optional;

@Service
@Transactional
public class EnrolmentCommandImplService  implements EnrolmentCommandService {

    private EnrolmentRepo enrolmentRepo;

    private StudentRepo studentRepo;

    private CourseRepo courseRepo;

    public EnrolmentCommandImplService(EnrolmentRepo enrolmentRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.enrolmentRepo = enrolmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public void addEnrolment(EnrolmentDTO enrolmentDTO) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(enrolmentDTO.getCourseName(), enrolmentDTO.getStudentFirstName(), enrolmentDTO.getStudentLastName());

        if (enrolment.isEmpty()) {

            Optional<Student> student = studentRepo.findStudentByEmail(enrolmentDTO.getStudentEmail());

            if (student.isEmpty()) {
                throw new StudentNotFoundException();
            }
            Optional<Course> course = courseRepo.getCourseByName(enrolmentDTO.getCourseName());

            if (course.isEmpty()) {
                throw new CourseNotFoundException();
            }

            Enrolment enrolment1 = Enrolment.builder().student(student.get()).course(course.get()).build();

            enrolmentRepo.save(enrolment1);
        } else {
            throw new EnrolmentWasFoundException();
        }

    }

    public void deleteEnrolment(long id) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentById(id);

        if (enrolment.isEmpty()) {
            throw new EnrolmentNotFoundException();
        } else {
            enrolmentRepo.delete(enrolment.get());
        }

    }

    public void updateEnrolment(EnrolmentDTO enrolmentDTO) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(enrolmentDTO.getCourseName(), enrolmentDTO.getStudentFirstName(), enrolmentDTO.getStudentLastName());

        if (enrolment.isEmpty()) {
            throw new EnrolmentNotFoundException();
        } else {

            Optional<Student> student = studentRepo.findStudentByEmail(enrolmentDTO.getStudentEmail());

            if(student.isEmpty()){
                throw new StudentNotFoundException();
            }

            Optional<Course> course = courseRepo.getCourseByName(enrolmentDTO.getCourseName());

            if(course.isEmpty()){
                throw new CourseNotFoundException();
            }

            enrolment.get().setStudent(student.get());
            enrolment.get().setCourse(course.get());

            enrolmentRepo.saveAndFlush(enrolment.get());


        }
    }
}
