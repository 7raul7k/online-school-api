package ro.myclass.onlineschoolapi.enrolment.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.dto.RemoveEnrolmentDTO;
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

        StudentDTO studentDTO = enrolmentDTO.getStudentdto();
        CourseDTO courseDTO = enrolmentDTO.getCourseDTO();


        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(courseDTO.getName(),studentDTO.getFirstName(),studentDTO.getLastName());


        if (enrolment.isEmpty()) {

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if (student.isEmpty()) {
                throw new StudentNotFoundException();
            }
            Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

            if (course.isEmpty()) {
                throw new CourseNotFoundException();
            }

            Enrolment enrolment1 = Enrolment.builder().student(student.get()).course(course.get()).build();

            enrolmentRepo.save(enrolment1);
        } else {
            throw new EnrolmentWasFoundException();
        }

    }

    public void deleteEnrolment(RemoveEnrolmentDTO removeEnrolmentDTO) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(removeEnrolmentDTO.getCourseName(), removeEnrolmentDTO.getFirstName(), removeEnrolmentDTO.getLastName());

        if (enrolment.isEmpty()) {
            throw new EnrolmentNotFoundException();
        } else {
            enrolmentRepo.delete(enrolment.get());
        }

    }

    public void updateEnrolment(EnrolmentDTO enrolmentDTO) {

        StudentDTO studentDTO = enrolmentDTO.getStudentdto();
        CourseDTO courseDTO = enrolmentDTO.getCourseDTO();


        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentByCourseNameAndStudentFirstNameAndStudentLastName(courseDTO.getName(), studentDTO.getFirstName(), studentDTO.getLastName());



        if (enrolment.isEmpty()) {
            throw new EnrolmentNotFoundException();
        } else {

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if(student.isEmpty()){
                throw new StudentNotFoundException();
            }

            Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

            if(course.isEmpty()){
                throw new CourseNotFoundException();
            }

            enrolment.get().setStudent(student.get());
            enrolment.get().setCourse(course.get());

            enrolmentRepo.saveAndFlush(enrolment.get());


        }
    }
}
